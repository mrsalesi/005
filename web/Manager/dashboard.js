$(function() {

  'use strict';

  // Datepicker
  $('.datepicker').datepicker({
    showOtherMonths: true,
    selectOtherMonths: true
  });

  // Flot Chart
  var newCust = [[0, 2], [1, 3], [2,6], [3, 5], [4, 7], [5, 8], [6, 10], [7, 8], [8, 5], [9, 7], [10, 4]];
  var retCust = [[0, 1], [1, 2], [2,5], [3, 3], [4, 5], [5, 6], [6, 9], [7, 6], [8, 3], [9, 5], [10, 2]];

  var plot = $.plot($('#flotArea'),[{
    data: newCust,
    label: 'سال 97',
    color: '#354763'
  },
  {
    data: retCust,
    label: 'سال 96',
    color: '#7CBDDF'
  }],
  {
    series: {
      lines: {
        show: false
      },
      splines: {
        show: true,
        tension: 0.4,
        lineWidth: 0,
        fill: 0.8
      },
      shadowSize: 0
    },
    points: {
      show: false,
    },
    legend: {
      noColumns: 1,
      position: 'nw'
    },
    grid: {
      hoverable: true,
      clickable: true,
      borderColor: '#ddd',
      borderWidth: 0,
      labelMargin: 5,
      backgroundColor: '#fff'
    },
    yaxis: {
      min: 0,
      max: 15,
      color: '#eee',
      font: {
        size: 10,
        color: '#999'
      }
    },
    xaxis: {
      color: '#eee',
      font: {
        size: 10,
        color: '#999'
      }
    }
  });


  // Real Time Updates
  var data = [], totalPoints = 50;

  function getRandomData() {
    if (data.length > 0)
    data = data.slice(1);
    while (data.length < totalPoints) {
      var prev = data.length > 0 ? data[data.length - 1] : 50,
      y = prev + Math.random() * 10 - 5;
      if (y < 0) {
        y = 0;
      } else if (y > 100) {
        y = 100;
      }
      data.push(y);
    }
    var res = [];
    for (var i = 0; i < data.length; ++i) {
      res.push([i, data[i]])
    }
    return res;
  }


  // Set up the control widget
  var updateInterval = 1000;

});
 