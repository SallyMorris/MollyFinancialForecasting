package com.molly.forecasting.naivebayes;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Data {
	
	static String[][] stock = {
			{"DayOfWeek","Price Direction"},
			{"Saturday","DOWN"},
			{"Sunday","UP"},
			{"Monday","UP"},
			{"Tuesday","DOWN"},
			{"Wednesday","DOWN"},
			{"Thursday","UP"},
			{"Friday","UP"},
			{"Saturday","DOWN"},
			{"Sunday","UP"},
			{"Monday","DOWN"},
			{"Tuesday","DOWN"},
			{"Wednesday","DOWN"},
			{"Thursday","UP"},
			{"Friday","UP"},
			{"Saturday","DOWN"},
			{"Sunday","UP"},
			{"Monday","DOWN"},
			{"Tuesday","UP"},
			{"Wednesday","DOWN"},
			{"Thursday","UP"},
			{"Friday","DOWN"},
			{"Saturday","DOWN"},
		};
	static Map<String,String [][]> datas =  Collections.unmodifiableMap(new HashMap<String,String [][]>(){
		private static final long serialVersionUID = 1L;
		{
			put("STOCK",stock);
		}
	});
}
