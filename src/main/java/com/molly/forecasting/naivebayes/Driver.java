package com.molly.forecasting.naivebayes;

import java.util.HashMap;
import java.util.Iterator;

public class Driver {

	public static void main(String[] args) throws Exception {
		String[][] stock = { { "DayOfWeek", "Price Direction" }, { "Saturday", "DOWN" }, { "Sunday", "UP" },
				{ "Monday", "UP" }, { "Tuesday", "DOWN" }, { "Wednesday", "DOWN" }, { "Thursday", "UP" },
				{ "Friday", "UP" }, { "Saturday", "DOWN" }, { "Sunday", "UP" }, { "Monday", "DOWN" },
				{ "Tuesday", "DOWN" }, { "Wednesday", "DOWN" }, { "Thursday", "UP" }, { "Friday", "UP" },
				{ "Saturday", "DOWN" }, { "Sunday", "UP" }, { "Monday", "DOWN" }, { "Tuesday", "UP" },
				{ "Wednesday", "DOWN" }, { "Thursday", "UP" }, { "Friday", "DOWN" }, { "Saturday", "DOWN" }, };
		DataSet dataSet = new DataSet(stock);
		System.out.println("[DATASET]\n" + dataSet);
		String dayOfWeek = "Friday";
		HashMap<String, String> instMap = new HashMap<String, String>();
		instMap.put(dataSet.getData()[0][0], dayOfWeek);

		HashMap<String, Double> condProbs = dataSet.calcCondProbs(instMap);
		double allProbs = 0;
		Iterator<Double> probsIterator = condProbs.values().iterator();
		while (probsIterator.hasNext()) {
			allProbs += probsIterator.next();
		}
		Iterator<String> keyIterator = condProbs.keySet().iterator();
		StringBuffer resultSB = new StringBuffer();
		while (keyIterator.hasNext()) {
			String next = keyIterator.next();
			resultSB.append("P(" + next + "|" + DataSet.getInstanceStr(dataSet, instMap) + ") = "
					+ String.format("%.5f", condProbs.get(next)) + "/" + String.format("%.5f", allProbs) + " = "
					+ String.format("%.5f", condProbs.get(next) / allProbs)+"\n");
		}
		System.out.println(resultSB.toString());

	}

}
