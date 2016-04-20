package ie.jtc.nearby.json;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;



import ie.jtc.nearby.exception.InputException;

/**
 * invoke the code as an application
 * @author John
 *
 */
public class Run {

	public static void main(String[] args) throws MalformedURLException, IOException, InputException {
		String url = "https://gist.github.com/brianw/19896c50afa89ad4dec3/raw/6c11047887a03483c50017c1d451667fd62a53ca/gistfile1.txt";
		int maxpeople=100;
		int kmAway=100;
		String baseLongLat="-6.2592576,53.3381985";
		if( args.length >=1){
			url=args[0];
		}
		if( args.length>=2){
			maxpeople=Integer.parseInt(args[1]);
		}
		if( args.length>=3){
			kmAway=Integer.parseInt(args[2]);
		}		
		if( args.length>=4){
			baseLongLat=args[3];
		}
		List<PersonAndLocation> customerList = PeopleData.getFromUrl(new URL(url));
		LongLat base=new LongLat(baseLongLat);
		List<PersonAndLocation> selectFew = PeopleData.removeTooManyKmAway(customerList,base,kmAway );
		PeopleData.output(System.out,selectFew,maxpeople,base);
		
	}

}
