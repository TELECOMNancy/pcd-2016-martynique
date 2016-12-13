package models;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import youtube.Auth;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class Search {
	
    /**
     * Define a global variable that identifies the name of a file that
     * contains the developer's API key.
     */
    private static final String PROPERTIES_FILENAME = "youtube.properties";

    private static final long NUMBER_OF_VIDEOS_RETURNED = 25;

    /**
     * Define a global instance of a Youtube object, which will be used
     * to make YouTube Data API requests.
     */
    private static YouTube youtube;
    
    private SearchListResponse searchResponse;
    private List<SearchResult> searchResultList;
    
    private List<Video> searchVideoList;
    
    private String searchInput;
    
    Properties properties = new Properties();
    
    public Search(String Search){
    	this.searchInput = Search;
    	searchVideoList = new ArrayList<Video>(); 
    	loadPropertiesFile();
    }
    
    public Search(){
    	
    	loadPropertiesFile();
    }
    

    
    public String getsearchInput(){
    	return searchInput;
    }
    
    public void printResult(){
        if (searchResultList != null) {
            prettyPrint(searchResultList.iterator(), searchInput);
        }
    }
    
    public SearchListResponse getSearchResponse(){
    	return searchResponse;
    }
    
    public List<Video> getVideoList(){
    	return searchVideoList;
    }
    
    public void setSearchInput(String newString){
    	this.searchInput = newString;
    }
    
    /** Load youtube.properties to create Properties Object. For example Load APiI Key
     */
    public void loadPropertiesFile(){
    	this.properties = new Properties();
        try {
            InputStream in = Search.class.getResourceAsStream("/" + PROPERTIES_FILENAME);
            properties.load(in);

        } catch (IOException e) {
            System.err.println("There was an error reading " + PROPERTIES_FILENAME + ": " + e.getCause()
                    + " : " + e.getMessage());
            System.exit(1);
        }
    	
    }
    
    public void executeApiRequest(){
        try {
            // This object is used to make YouTube Data API requests. The last
            // argument is required, but since we don't need anything
            // initialized when the HttpRequest is initialized, we override
            // the interface and provide a no-op function.
            youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {
                }
            }).setApplicationName("youtube-cmdline-search-sample").build();

            // Prompt the user to enter a query term.
            String queryTerm = searchInput;

            // Define the API request for retrieving search results.
            YouTube.Search.List search = youtube.search().list("id,snippet");

            // Set your developer key from the {{ Google Cloud Console }} for
            // non-authenticated requests. See:
            // {{ https://cloud.google.com/console }}
            String apiKey = properties.getProperty("youtube.apikey");
            System.out.println(apiKey);
            search.setKey(apiKey);
            search.setQ(queryTerm);

            // Restrict the search results to only include videos. See:
            // https://developers.google.com/youtube/v3/docs/search/list#type
            search.setType("video");

            // To increase efficiency, only retrieve the fields that the
            // application uses.
            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
            
            
            // Call the API and print results.
            this.searchResponse = search.execute();
            this.searchResultList = searchResponse.getItems();
            
            Iterator<SearchResult> iterator = searchResultList.iterator();
            
            if (!iterator.hasNext()) {
                System.out.println(" There aren't any results for your query22.");
            }

            while (iterator.hasNext()) {
            	SearchResult singleVideo = iterator.next();
                ResourceId rId = singleVideo.getId();
                
            	if (rId.getKind().equals("youtube#video")) {
            	
            	String videoID = rId.getVideoId();

                Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().getDefault();
                
                String title = singleVideo.getSnippet().getTitle();
                String thumbnails = thumbnail.getUrl();
            	Video video = new Video(title,thumbnails,videoID);
            	this.searchVideoList.add(video);
            	}
            	
            }
            


        } catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }
        
    }
    
    private static void prettyPrint(Iterator<SearchResult> iteratorSearchResults, String query) {

        System.out.println("\n=============================================================");
        System.out.println(
                "   First " + NUMBER_OF_VIDEOS_RETURNED + " videos for search on \"" + query + "\".");
        System.out.println("=============================================================\n");

        if (!iteratorSearchResults.hasNext()) {
            System.out.println(" There aren't any results for your query.");
        }

        while (iteratorSearchResults.hasNext()) {

            SearchResult singleVideo = iteratorSearchResults.next();
            ResourceId rId = singleVideo.getId();

            // Confirm that the result represents a video. Otherwise, the
            // item will not contain a video ID.
            if (rId.getKind().equals("youtube#video")) {
                Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().getDefault();

                System.out.println(" Video Id" + rId.getVideoId());
                System.out.println(" Title: " + singleVideo.getSnippet().getTitle());
                System.out.println(" Thumbnail: " + thumbnail.getUrl());

                System.out.println("\n-------------------------------------------------------------\n");
            }
        }
    }
    

}
