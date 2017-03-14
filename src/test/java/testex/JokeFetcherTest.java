/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testex;

import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import testex.jokefetching.ChuckNorris;
import testex.jokefetching.EduJoke;
import testex.jokefetching.IFetcherFactory;
import testex.jokefetching.IJokeFetcher;
import testex.jokefetching.Moma;
import testex.jokefetching.Tambal;

/**
 *
 * @author Mato
 */
@RunWith(MockitoJUnitRunner.class)
public class JokeFetcherTest {

    private JokeFetcher jokeFetcher;

    @Mock
    private IDateFormatter ifMock;
    @Mock
    private IFetcherFactory factory;

    @Mock
    Moma moma;
    @Mock
    ChuckNorris chuck;
    @Mock
    EduJoke edu;
    @Mock
    Tambal tambal;

    @Before
    public void setup() {
        List<IJokeFetcher> fetchers = Arrays.asList(edu, chuck, moma, tambal);
        when(factory.getJokeFetchers("EduJoke,ChuckNorris,Moma,Tambal")).thenReturn(fetchers);
        List<String> types = Arrays.asList("EduJoke", "ChuckNorris", "Moma", "Tambal");
        when(factory.getAvailableTypes()).thenReturn(types);
        jokeFetcher = new JokeFetcher(ifMock, factory);

        Joke testEdu = new Joke("Educational haha joke", "AwesomeEduJokes.com");
        Joke testChuck = new Joke("Chuck Norris haha joke", "AwesomeChuckNorrisJokes.com");
        Joke testMoma = new Joke("Yo mama ihaha joke", "AwesomeYoMamaJokes.com");
        Joke testTambal = new Joke("Tambala never heard this one", "AwesomeTambalaJokes.com");

        given(edu.getJoke()).willReturn(testEdu);
        given(chuck.getJoke()).willReturn(testChuck);
        given(moma.getJoke()).willReturn(testMoma);
        given(tambal.getJoke()).willReturn(testTambal);
    }

    public JokeFetcherTest() {
    }

    @Test
    public void testGetAvailableTypes() {
        assertThat(jokeFetcher.getAvailableTypes(), hasItems("eduprog", "chucknorris", "moma", "tambal"));
        assertThat(jokeFetcher.getAvailableTypes().size(), is(4));
    }

    @Test
    public void checkIfValidToken() {
        String valid = "eduprog,chucknorris,moma,tambal";
        assertThat(jokeFetcher.checkIfValidToken(valid), is(true));
        String invalid = "wannabeNorris,mammaMia,tabooooo,wohohhooo";
        assertThat(jokeFetcher.checkIfValidToken(invalid), is(false));
    }

    @Test
    public void testGetJokes() throws Exception {
        given(ifMock.getFormattedDate(eq("Europe/Copenhagen"), anyObject())).willReturn("17 feb. 2017 10:56 AM");
        assertThat(jokeFetcher.getJokes("eduprog,chucknorris,moma,tambal", "Europe/Copenhagen").getTimeZoneString(), is("17 feb. 2017 10:56 AM"));
    }

    @Test
    public void testMain() throws Exception {
    }

    @Test
    public void testChuckNorrisJoke() throws Exception {
        String expectedJoke = "Chuck Norris haha joke";
        String expectedUrl = "AwesomeChuckNorrisJokes.com";
        Jokes jokes = jokeFetcher.getJokes("EduJoke,ChuckNorris,Moma,Tambal", "Europe/Copenhagen");
        assertThat(jokes.getJokes().get(1).getJoke(), is(expectedJoke));
        assertThat(jokes.getJokes().get(1).getReference(), is(expectedUrl));
    }

    @Test
    public void testEduJoke() throws Exception {
        String expectedJoke = "Educational haha joke";
        String expectedUrl = "AwesomeEduJokes.com";
        Jokes jokes = jokeFetcher.getJokes("EduJoke,ChuckNorris,Moma,Tambal", "Europe/Copenhagen");
        assertThat(jokes.getJokes().get(0).getJoke(), is(expectedJoke));
        assertThat(jokes.getJokes().get(0).getReference(), is(expectedUrl));
    }

    @Test
    public void testYoMamaJoke() throws Exception {
        String expectedJoke = "Yo mama ihaha joke";
        String expectedUrl = "AwesomeYoMamaJokes.com";
        Jokes jokes = jokeFetcher.getJokes("EduJoke,ChuckNorris,Moma,Tambal", "Europe/Copenhagen");
        assertThat(jokes.getJokes().get(2).getJoke(), is(expectedJoke));
        assertThat(jokes.getJokes().get(2).getReference(), is(expectedUrl));
    }

    @Test
    public void testTambalaJoke() throws Exception {
        String expectedJoke = "Tambala never heard this one";
        String expectedUrl = "AwesomeTambalaJokes.com";
        Jokes jokes = jokeFetcher.getJokes("EduJoke,ChuckNorris,Moma,Tambal", "Europe/Copenhagen");
        assertThat(jokes.getJokes().get(3).getJoke(), is(expectedJoke));
        assertThat(jokes.getJokes().get(3).getReference(), is(expectedUrl));
    }

}
