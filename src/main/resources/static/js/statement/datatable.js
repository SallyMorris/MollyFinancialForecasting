$(document).ready(function () {
	$('#graphSelection').niceSelect();
	$('#multipleYearsSelect').multiSelect();
	$('#multipleSelect').multiSelect();
	$('#comparisonResults').hide();
	$('#graphResults').hide();
	$('#loading').hide();
	$('#compare').hide();

	var result;
	var tickers;
	var years;
	var idForStatementTable = 1;

	var investmentSecuritiesInterestIncome = [];
	var totalRevenue = [];
	var totalInterestIncome = [];
	var totalInterestExpense = [];
	var salariesAndEmployeeBenefitsExpense = [];
	var netIncome = [];
	var cashAndEquivalents = [];
	var totalAssets = [];
	var totalLiabilities = [];
	var totalCommonequity = [];
	var totalEquity = [];
	var leverageRatio = [];
	var nonControllingInterestSharingRatio = [];
	var divPayoutRatio = [];
	var augmentedPayoutRatio = [];


	$("#multipleSelect").change(function () {
		tickers = $('#multipleSelect').val();
		if (typeof years !== 'undefined' && years.length > 0 && typeof tickers !== 'undefined' && tickers.length > 0) {
			$('#compare').show();
		} else {
			$('#compare').hide();
			$('#comparisonResults').hide();
		}
	});

	$("#multipleYearsSelect").change(function () {
		years = $('#multipleYearsSelect').val();
		if (typeof years !== 'undefined' && years.length > 0 && typeof tickers !== 'undefined' && tickers.length > 0) {
			$('#compare').show();
		} else {
			$('#compare').hide();
			$('#comparisonResults').hide();
		}
	});

	$("#compare").click(function () {
		$('#statementDataTable').empty();
		$('#comparisonResults').hide();
		$('#compare').hide();
		$('#loading').show();
		var index;
		for (index = 0; index < tickers.length; index++) {
			var DateTable = '<table id="StatementTable' + idForStatementTable + '" class="stripe" style="width:100%"><thead><tr><th>Year</th><th>Investment Securities Interest Income</th><th>Total Revenue</th><th>Total Interest Income</th><th>Total Interest Expense</th><th>Salaries And Employee Benefits Expense</th><th>Net Income</th><th>Cash And Equivalents</th><th>Total Assets</th><th>Total Liabilities</th><th>Total Common Equity</th><th>Total Equity</th><th>Leverage Ratio</th><th>Non Controlling Interest Sharing Ratio</th><th>Div Payout Ratio</th><th>Augmented Payout Ratio</th></tr></thead></table>';
			$('<h3>' + $('#multipleSelect option[value=' + tickers[index] + ']').text() + '</h3>').appendTo('#statementDataTable');
			$(DateTable).appendTo('#statementDataTable');
			$('<br>').appendTo('#statementDataTable');
			if (index != tickers.length - 1) {
				$('<hr style="height:1px;border:none;color:#333;background-color:#333;" />').appendTo('#statementDataTable');
			}

			$('<br>').appendTo('#statementDataTable');

			$.ajaxSetup({
				async: false
			});

			$.getJSON('/api/statement', { ticker: tickers[index], years: years.join() }, function (list) {
				result = list;
			});

			$.ajaxSetup({
				async: true
			});

			$('#StatementTable' + idForStatementTable + '').removeAttr('width').DataTable({
				"scrollX": true,
				"scrollCollapse": true,
				"data": result,
				"columns": [
					{ "data": "year" },
					{ "data": "investmentSecuritiesInterestIncome" },
					{ "data": "totalRevenue" },
					{ "data": "totalInterestIncome" },
					{ "data": "totalInterestExpense" },
					{ "data": "salariesAndEmployeeBenefitsExpense" },
					{ "data": "netIncome" },
					{ "data": "cashAndEquivalents" },
					{ "data": "totalAssets" },
					{ "data": "totalLiabilities" },
					{ "data": "totalCommonequity" },
					{ "data": "totalEquity" },
					{ "data": "leverageRatio" },
					{ "data": "nonControllingInterestSharingRatio" },
					{ "data": "divPayoutRatio" },
					{ "data": "augmentedPayoutRatio" }
				],
				dom: 'Bfrtip',
				buttons: [
					{ extend: 'excel', filename: $('#multipleSelect option[value=' + tickers[index] + ']').text() + ' Statement', title: $('#multipleSelect option[value=' + tickers[index] + ']').text() + ' Statement' },
					{ extend: 'pdf', filename: $('#multipleSelect option[value=' + tickers[index] + ']').text() + ' Statement', title: $('#multipleSelect option[value=' + tickers[index] + ']').text() + ' Statement' },
					{ extend: 'print', title: $('#multipleSelect option[value=' + tickers[index] + ']').text() + ' Statement' }
				],
				"order": [[0, "desc"]]
			});

			//initliaze the data for every finncial attribute for charts
			//###############################################################################
			var investmentSecuritiesInterestIncomeObj = {};
			investmentSecuritiesInterestIncomeObj.name = $('#multipleSelect option[value=' + tickers[index] + ']').text();
			investmentSecuritiesInterestIncomeObj.data = [];
			var j;
			for (j = 0; j < result.length; j++) {
				investmentSecuritiesInterestIncomeObj.data.push(result[j].investmentSecuritiesInterestIncome);
			}
			investmentSecuritiesInterestIncome.push(investmentSecuritiesInterestIncomeObj);
			//###############################################################################
			var totalRevenueObj = {};
			totalRevenueObj.name = $('#multipleSelect option[value=' + tickers[index] + ']').text();
			totalRevenueObj.data = [];
			var k;
			for (k = 0; k < result.length; k++) {
				totalRevenueObj.data.push(result[k].totalRevenue);
			}
			totalRevenue.push(totalRevenueObj);
			//###############################################################################
			var totalInterestIncomeObj = {};
			totalInterestIncomeObj.name = $('#multipleSelect option[value=' + tickers[index] + ']').text();
			totalInterestIncomeObj.data = [];
			var l;
			for(l=0; l <result.length;l++){
				totalInterestIncomeObj.data.push(result[l].totalInterestIncome);
			}
			totalInterestIncome.push(totalInterestIncomeObj);
			//###############################################################################
			var totalInterestExpenseObj = {};
			totalInterestExpenseObj.name = $('#multipleSelect option[value=' + tickers[index] + ']').text();
			totalInterestExpenseObj.data = [];
			var m;
			for(m=0; m <result.length;m++){
				totalInterestExpenseObj.data.push(result[m].totalInterestExpense);
			}
			totalInterestExpense.push(totalInterestExpenseObj);
			//###############################################################################
			var salariesAndEmployeeBenefitsExpenseObj = {};
			salariesAndEmployeeBenefitsExpenseObj.name = $('#multipleSelect option[value=' + tickers[index] + ']').text();
			salariesAndEmployeeBenefitsExpenseObj.data = [];
			var n;
			for(n=0; n <result.length;n++){
				salariesAndEmployeeBenefitsExpenseObj.data.push(result[n].salariesAndEmployeeBenefitsExpense);
			}
			salariesAndEmployeeBenefitsExpense.push(salariesAndEmployeeBenefitsExpenseObj);
			//###############################################################################
			var netIncomeObj = {};
			netIncomeObj.name = $('#multipleSelect option[value=' + tickers[index] + ']').text();
			netIncomeObj.data = [];
			var o;
			for(o=0; o <result.length;o++){
				netIncomeObj.data.push(result[o].netIncome);
			}
			netIncome.push(netIncomeObj);
			//###############################################################################
			var cashAndEquivalentsObj = {};
			cashAndEquivalentsObj.name = $('#multipleSelect option[value=' + tickers[index] + ']').text();
			cashAndEquivalentsObj.data = [];
			var p;
			for(p=0; p <result.length;p++){
				cashAndEquivalentsObj.data.push(result[p].cashAndEquivalents);
			}
			cashAndEquivalents.push(cashAndEquivalentsObj);
			//###############################################################################
			var totalAssetsObj = {};
			totalAssetsObj.name = $('#multipleSelect option[value=' + tickers[index] + ']').text();
			totalAssetsObj.data = [];
			var q;
			for(q=0; q <result.length;q++){
				totalAssetsObj.data.push(result[q].totalAssets);
			}
			totalAssets.push(totalAssetsObj);
			//###############################################################################
			var totalLiabilitiesObj = {};
			totalLiabilitiesObj.name = $('#multipleSelect option[value=' + tickers[index] + ']').text();
			totalLiabilitiesObj.data = [];
			var r;
			for(r=0; r <result.length;r++){
				totalLiabilitiesObj.data.push(result[r].totalLiabilities);
			}
			totalLiabilities.push(totalLiabilitiesObj);
			//###############################################################################
			var totalCommonequityObj = {};
			totalCommonequityObj.name = $('#multipleSelect option[value=' + tickers[index] + ']').text();
			totalCommonequityObj.data = [];
			var s;
			for(s=0; s <result.length;s++){
				totalCommonequityObj.data.push(result[s].totalCommonequity);
			}
			totalCommonequity.push(totalCommonequityObj);
			//###############################################################################
			var totalEquityObj = {};
			totalEquityObj.name = $('#multipleSelect option[value=' + tickers[index] + ']').text();
			totalEquityObj.data = [];
			var t;
			for(t=0; t <result.length;t++){
				totalEquityObj.data.push(result[t].totalEquity);
			}
			totalEquity.push(totalEquityObj);
			//###############################################################################
			var leverageRatioObj = {};
			leverageRatioObj.name = $('#multipleSelect option[value=' + tickers[index] + ']').text();
			leverageRatioObj.data = [];
			var u;
			for(u=0; u <result.length;u++){
				leverageRatioObj.data.push(result[u].leverageRatio);
			}
			leverageRatio.push(leverageRatioObj);
			//###############################################################################
			var nonControllingInterestSharingRatioObj = {};
			nonControllingInterestSharingRatioObj.name = $('#multipleSelect option[value=' + tickers[index] + ']').text();
			nonControllingInterestSharingRatioObj.data = [];
			var v;
			for(v=0; v <result.length;v++){
				nonControllingInterestSharingRatioObj.data.push(result[v].nonControllingInterestSharingRatio);
			}
			nonControllingInterestSharingRatio.push(nonControllingInterestSharingRatioObj);
			//###############################################################################
			var divPayoutRatioObj = {};
			divPayoutRatioObj.name = $('#multipleSelect option[value=' + tickers[index] + ']').text();
			divPayoutRatioObj.data = [];
			var w;
			for(w=0; w <result.length;w++){
				divPayoutRatioObj.data.push(result[w].divPayoutRatio);
			}
			divPayoutRatio.push(divPayoutRatioObj);
			//###############################################################################
			var augmentedPayoutRatioObj = {};
			augmentedPayoutRatioObj.name = $('#multipleSelect option[value=' + tickers[index] + ']').text();
			augmentedPayoutRatioObj.data = [];
			var x;
			for(x=0; x <result.length;x++){
				augmentedPayoutRatioObj.data.push(result[x].augmentedPayoutRatio);
			}
			augmentedPayoutRatio.push(augmentedPayoutRatioObj);
			//###############################################################################

			idForStatementTable++;
		}
		$('#loading').hide();
		$('#compare').show();
		$('#comparisonResults').show();
		$('#graphResults').show();

	});

	$("#graphSelection").change(function () {
		console.log(investmentSecuritiesInterestIncome);
		console.log(totalRevenue);
		var attributeName = $("#graphSelection option:selected").text();
		var selectedVal = $("#graphSelection").val();
		if (selectedVal == "investmentSecuritiesInterestIncome") {
			fireChart(attributeName, investmentSecuritiesInterestIncome);
		} else if (selectedVal == "totalRevenue") {
			fireChart(attributeName, totalRevenue);
		}else if (selectedVal == "totalInterestIncome"){
			fireChart(attributeName, totalInterestIncome);
		}else if (selectedVal == "totalInterestExpense"){
			fireChart(attributeName, totalInterestExpense);
		}else if (selectedVal == "salariesAndEmployeeBenefitsExpense"){
			fireChart(attributeName, salariesAndEmployeeBenefitsExpense);
		}else if (selectedVal == "netIncome"){
			fireChart(attributeName, netIncome);
		}else if (selectedVal == "cashAndEquivalents"){
			fireChart(attributeName, cashAndEquivalents);
		}else if (selectedVal == "totalAssets"){
			fireChart(attributeName, totalAssets);
		}else if (selectedVal == "totalLiabilities"){
			fireChart(attributeName, totalLiabilities);
		}else if (selectedVal == "totalCommonequity"){
			fireChart(attributeName, totalCommonequity);
		}else if (selectedVal == "leverageRatio"){
			fireChart(attributeName, leverageRatio);
		}else if (selectedVal == "nonControllingInterestSharingRatio"){
			fireChart(attributeName, nonControllingInterestSharingRatio);
		}else if (selectedVal == "divPayoutRatio"){
			fireChart(attributeName, divPayoutRatio);
		}else if (selectedVal == "augmentedPayoutRatio"){
			fireChart(attributeName, augmentedPayoutRatio);
		}

	});

	var fireChart = function (attribute, seriesData) {
		Highcharts.chart('containerForGraph', {
			chart: {
				type: 'line'
			},
			title: {
				text: attribute
			},
			subtitle: {
				text: 'Source: https://intrinio.com/'
			},
			xAxis: {
				categories: years
			},
			yAxis: {
				title: {
					text: attribute
				}
			},
			plotOptions: {
				line: {
					dataLabels: {
						enabled: true
					},
					enableMouseTracking: true
				}
			},
			series: seriesData
		});
	}

});
