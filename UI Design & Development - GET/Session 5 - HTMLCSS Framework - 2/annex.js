const hm=document.querySelector("#toggle-btn");
hm.addEventListener("click",function(){
    document.querySelector("#sidebar").classList.toggle("expand");
    toggler.classList.toggle("bxs-checrons-right");
    toggler.classList.toggle("bxs-checrons-left");
});

new Chart(document.getElementById("line-chart"), {
    type: 'line',
    data: {
      labels: [2009,2010 ,2011,2012,2013,2014,2015],
      datasets: [{ 
          data: [0,150,20,150,20,50,150],
          label: "Series A",
          borderColor: "#3e95cd",
          fill: false
        }, { 
          data: [0,30,50,80,110,150,170],
          label: "Series B",
          borderColor: "#8e5ea2",
          fill: false
        }, { 
          data: [0,50,150,40,150,40,130],
          label: "Europe",
          borderColor: "#3cba9f",
          fill: false
        }
      ]
    },
    options: {
      title: {
        display: true,
        text: 'World population per region (in millions)'
      }
    }
  });


  new Chart(document.getElementById("doughnut-chart"), {
    type: 'doughnut',
    data: {
      labels: ["Canada","USA","LONDON"],
      datasets: [
        {
          label: "(millions)",
          backgroundColor: ["#3e95cd", "#8e5ea2","#3cba9f"],
          data: [30,12,20]
        }
      ]
    },
    options: {
      title: {
        display: true,
        text: 'Predicted world population (millions) in 2050'
      }
    }
});

var myIndex = 0;
carousel();

function carousel() {
  var i;
  var x = document.getElementsByClassName("mySlides");
  for (i = 0; i < x.length; i++) {
    x[i].style.display = "none";  
  }
  myIndex++;
  if (myIndex > x.length) {myIndex = 1}    
  x[myIndex-1].style.display = "block";  
  setTimeout(carousel, 2500);    
}


var myIndex_2 = 0;
carousel_2();

function carousel_2() {
  var i;
  var x = document.getElementsByClassName("mySlides-2");
  for (i = 0; i < x.length; i++) {
    x[i].style.display = "none";  
  }
  myIndex_2++;
  if (myIndex > x.length) {myIndex = 1}    
  x[myIndex-1].style.display = "block";  
  setTimeout(carousel_2, 2500);    
}
new Chart(document.getElementById("line-chart-2"), {
  type: 'line',
  data: {
    labels: ["Point 1", "Point 2", "Point 3", "Point 4", "Point 5", "Point 6"],
    datasets: [{ 
        data: [15, 35, 25, 55, 30, 40,10,25], 
        label: "Mountain 1",
        borderColor: "#3e95cd",
        backgroundColor: "rgba(62, 149, 205, 0.5)", 
        fill: true
      }, { 
        data: [10, 30, 10, 45, 20, 35,25,30], 
        label: "Mountain 2",
        borderColor: "#8e5ea2",
        backgroundColor: "rgba(142, 94, 162, 0.5)",
        fill: true
      }
    ]
  },
  options: {
    plugins:{
      legend:{
        display:false
      },
      title: {
        display: false,
        text: 'Mountain-like Area Chart'
      },
    }, 
    scales: {
      y: {
        display:false,
        beginAtZero: true

      },
      x: {
        display:false
      }
    }
  }
});