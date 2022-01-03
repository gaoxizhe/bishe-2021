$(document).ready(function ( $ ) {
    "use strict";

    $.ajax({
    	type: "GET",
		url: "/Persephone/showTotalCountByDay",
		dataType: "json",
		statusCode: {
			404: function() {  
				alert('提交地址url未发现'); 
			}  
		}, 
		success: function (data) {
			var date = data.date;
			var coffee = data.coffee;
			var tea = data.tea;
			var milkTea = data.milkTea;
			var fruitTea = data.fruitTea;
			var milk = data.milk;
			
		    //Team chart
		    var ctx = document.getElementById( "team-chart" );
		    ctx.height = 150;
		    var myChart = new Chart( ctx, {
		        type: 'line',
		        data: {
//		            labels: [ "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" ],
		        	labels: date,
		            type: 'line',
		            defaultFontFamily: 'Montserrat',
		            datasets: [ {
//		                data: [ 90, 107, 93, 95, 92, 118, 120 ],
		            	data: milkTea,
		                label: "醇香奶茶",
		                backgroundColor: 'rgba(244,121,32,.35)',
		                borderColor: 'rgba(244,121,32,1)',
		                borderWidth: 3.5,
		                pointStyle: 'circle',
		                pointRadius: 5,
		                pointBorderColor: 'transparent',
		                pointBackgroundColor: 'rgba(244,121,32,0.60)',
		                    },
		                    {
//		                data: [ 83, 87, 90, 96, 101, 110, 125 ],
		                data: coffee,
		                label: "咖啡时光",
		                backgroundColor: 'rgba(135,72,31,.25)',
		                borderColor: 'rgba(135,72,31,1)',
		                borderWidth: 3.5,
		                pointStyle: 'circle',
		                pointRadius: 5,
		                pointBorderColor: 'transparent',
		                pointBackgroundColor: 'rgba(135,72,31,0.5)',
		                    },
		                    {
//		                data: [ 70, 65, 63, 59, 67, 75, 76 ],
		                data: milk,
		                label: "牧场牛奶",
		                backgroundColor: 'rgba(222,198,116,.25)',
		                borderColor: 'rgba(222,198,116,1)',
		                borderWidth: 3.5,
		                pointStyle: 'circle',
		                pointRadius: 5,
		                pointBorderColor: 'transparent',
		                pointBackgroundColor: 'rgba(222,198,116,0.5)',
		                    },
		                    {
//		                data: [ 95, 99, 100, 89, 91, 103, 105 ],
		                data: fruitTea,
		                label: "清新夏日",
		                backgroundColor: 'rgba(51,163,220,.25)',
		                borderColor: 'rgba(51,163,220,1)',
		                borderWidth: 3.5,
		                pointStyle: 'circle',
		                pointRadius: 5,
		                pointBorderColor: 'transparent',
		                pointBackgroundColor: 'rgba(51,163,220,0.5)',
		                     },
		                     {
//		                data: [ 56, 66, 69, 59, 60, 70, 71 ],
		                data: tea,
		                label: "原沏茗作",
		                backgroundColor: 'rgba(69,185,124,.25)',
		                borderColor: 'rgba(69,185,124,1)',
		                borderWidth: 3.5,
		                pointStyle: 'circle',
		                pointRadius: 5,
		                pointBorderColor: 'transparent',
		                pointBackgroundColor: 'rgba(69,185,124,0.5)',
		                     },
		                    ]
		        },
		        options: {
		            responsive: true,
		            tooltips: {
		                mode: 'index',
		                titleFontSize: 12,
		                titleFontColor: '#000',
		                bodyFontColor: '#000',
		                backgroundColor: '#fff',
		                titleFontFamily: 'Montserrat',
		                bodyFontFamily: 'Montserrat',
		                cornerRadius: 3,
		                intersect: false,
		            },
		            legend: {
		                display: true,
		                position: 'top',
		                labels: {
		                    usePointStyle: true,
		                    fontFamily: 'Montserrat',
		                },


		            },
		            scales: {
		                xAxes: [ {
		                    display: true,
		                    gridLines: {
		                        display: true,
		                        drawBorder: false
		                    },
		                    scaleLabel: {
		                        display: false,
		                        labelString: '星期'
		                    }
		                        } ],
		                yAxes: [ {
		                    display: true,
		                    gridLines: {
		                        display: true,
		                        drawBorder: false
		                    },
		                    scaleLabel: {
		                        display: true,
		                        labelString: '销量'
		                    }
		                        } ]
		            },
		            title: {
		                display: false,
		            }
		        }
		    });
		}
    });
    
} )( jQuery );