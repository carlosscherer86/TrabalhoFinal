package diversas;

public class DirectionsGoogle {
	public String status;
	public geocoded_waypoint[] geocoded_waypoints;
	public route[] routes;
	
	public class geocoded_waypoint {
		public String geocoder_status;
		public String partial_match;
		public String place_id;
		public String[] types;
	}
	
	public class route{
		public bound bounds;
		public String copyrights;
		public leg[] legs;
		public overview_polylin overview_polyline;
		public String summary;
		public String[] warnings;
		public String[] waypoint_order;
	}
	
	public class leg{
		public distances distance;
		public distances duration;
		public String end_address;
		public location end_location;
		public String start_address;
		public location start_location;
		public step[] steps;
		public String[] via_waypoint;
	}
	
	public class distances{
		public String text;
		public String value;
	}
	public class overview_polylin{
		public String points;
	}
	
	public class step{
		public distances distance;
		public distances duration;
		public location end_location;
		public String html_instructions;
		public overview_polylin polyline;
		public location start_location;
		public String travel_mode;
		
	}
	
	
	public class bound {

		public location northeast;
		public location southwest;
	}

	public class location {
		public String lat;
		public String lng;
	}
	
}
