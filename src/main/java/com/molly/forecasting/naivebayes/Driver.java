package com.molly.forecasting.naivebayes;

import java.util.Base64;

public class Driver {

	public static void main(String[] args) {
		String userNameANDPassword = "2b133ee185d14bf025458bde2348eb31"+":"+"15878798867d67e11397dd02587bb057";
		byte[] encodedBytes = Base64.getEncoder().encode(userNameANDPassword.getBytes());
		System.out.println("encodedBytes " + new String(encodedBytes));
//		String[][] stock = { { "DayOfWeek", "Price Direction" }, { "Saturday", "DOWN" }, { "Sunday", "UP" },
//				{ "Monday", "UP" }, { "Tuesday", "DOWN" }, { "Wednesday", "DOWN" }, { "Thursday", "UP" },
//				{ "Friday", "UP" }, { "Saturday", "DOWN" }, { "Sunday", "UP" }, { "Monday", "DOWN" },
//				{ "Tuesday", "DOWN" }, { "Wednesday", "DOWN" }, { "Thursday", "UP" }, { "Friday", "UP" },
//				{ "Saturday", "DOWN" }, { "Sunday", "UP" }, { "Monday", "DOWN" }, { "Tuesday", "UP" },
//				{ "Wednesday", "DOWN" }, { "Thursday", "UP" }, { "Friday", "DOWN" }, { "Saturday", "DOWN" }, };
//		DataSet dataSet = new DataSet(stock);
//		System.out.println("[DATASET]\n" + dataSet);
//		String dayOfWeek = "Friday";
//		HashMap<String, String> instMap = new HashMap<String, String>();
//		instMap.put(dataSet.getData()[0][0], dayOfWeek);
//
//		HashMap<String, Double> condProbs = dataSet.calcCondProbs(instMap);
//		double allProbs = 0;
//		Iterator<Double> probsIterator = condProbs.values().iterator();
//		while (probsIterator.hasNext()) {
//			allProbs += probsIterator.next();
//		}
//		Iterator<String> keyIterator = condProbs.keySet().iterator();
//		StringBuffer resultSB = new StringBuffer();
//		while (keyIterator.hasNext()) {
//			String next = keyIterator.next();
//			resultSB.append("P(" + next + "|" + DataSet.getInstanceStr(dataSet, instMap) + ") = "
//					+ String.format("%.5f", condProbs.get(next)) + "/" + String.format("%.5f", allProbs) + " = "
//					+ String.format("%.5f", condProbs.get(next) / allProbs)+"\n");
//		}
//		System.out.println(resultSB.toString());

	}

}
