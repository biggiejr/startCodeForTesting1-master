/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testex.jokefetching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Mato
 */
public class FetcherFactory implements IFetcherFactory {
    
    private final List<String> availableTypes = 
            Arrays.asList("EduJoke", "ChuckNorris", "Moma", "Tambal");
    
    private IJokeFetcher eduJoke;
    private IJokeFetcher chuckNorrisJoke;
    private IJokeFetcher momaJoke;
    private IJokeFetcher tambalJoke;

    public FetcherFactory() {
    }

    public FetcherFactory(IJokeFetcher eduJoke, IJokeFetcher chuckNorrisJoke, IJokeFetcher momaJoke, IJokeFetcher tambalJoke) {
        this.eduJoke = eduJoke;
        this.chuckNorrisJoke = chuckNorrisJoke;
        this.momaJoke = momaJoke;
        this.tambalJoke = tambalJoke;
    }
    
    

    @Override
    public List<String> getAvailableTypes() {
           return availableTypes;
    }

    @Override
    public List<IJokeFetcher> getJokeFetchers(String jokesToFetch) {
        List<IJokeFetcher> jokesListed = new ArrayList();
        String[] jokes = jokesToFetch.split(",");
        for(String joke : jokes){
            switch(joke) {
                case "ChuckNorris":
                    jokesListed.add(new ChuckNorris());
                    break;
                case "EduJoke":
                    jokesListed.add(new EduJoke());
                    break;
                case "Moma":
                    jokesListed.add(new Moma());
                    break;
                case "Tambal":
                    jokesListed.add(new Tambal());
                    break;
            }
        }
        return jokesListed;
    }
    
}