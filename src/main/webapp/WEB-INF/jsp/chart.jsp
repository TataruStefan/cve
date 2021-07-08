<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
window.onload = function() {

var dps = [[],[],[]];
var chart = new CanvasJS.Chart("chartContainer", {
	animationEnabled: true,
	theme: "light2",
	title: {
		text: "CVE Analysis"
	},
	axisX: {
		valueFormatString: "DDDD"
	},
	axisY: {
		title: "Severity"
	},
	legend: {
		cursor: "pointer",
		itemclick: toogleDataSeries
	},
	toolTip: {
		contentFormatter: function (e) {
			var str = CanvasJS.formatDate(e.entries[0].dataPoint.x, "DDDD") + "<br/>  <span style =' color:" + e.entries[0].dataSeries.color + "';>" + e.entries[0].dataSeries.name + "</span>: <strong>" + e.entries[0].dataPoint.y + " hrs</strong> <br/>";
			return str;
		}
	},
	data: [{
		name: "High",
		xValueType: "dateTime",
		showInLegend: true,
		type: "stackedArea",
		markerSize: 0,
		dataPoints: dps[0]
	}, {
		name: "Medium",
		xValueType: "dateTime",
		showInLegend: true,
		type: "stackedArea",
		markerSize: 0,
		dataPoints: dps[1]
	}, {
		name: "Low",
		xValueType: "dateTime",
		showInLegend: true,
		type: "stackedArea",
		markerSize: 0,
		dataPoints: dps[2]
	}
	]
});

var yValue;
var xValue;

<c:forEach items="${dataPointsList}" var="dataPoints" varStatus="loop">
	<c:forEach items="${dataPoints}" var="dataPoint">
		yValue = parseFloat("${dataPoint.y}");
		xValue = parseFloat("${dataPoint.x}");
		dps[parseInt("${loop.index}")].push({
			x : xValue,
			y : yValue,
		});
	</c:forEach>
</c:forEach>

chart.render();

function toogleDataSeries(e) {
	if (typeof (e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
		e.dataSeries.visible = false;
	} else {
		e.dataSeries.visible = true;
	}
	chart.render();
}

}
</script>
</head>
<body>
<div id="chartContainer" style="height: 370px; width: 100%;"></div>
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
</body>
</html>