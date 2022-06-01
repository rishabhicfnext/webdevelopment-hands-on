// var x = document.querySelector("li");
// console.log("length : "+x.classList.contains('highlight'));

// for (let i = 0; i < x.length; i++) {
//   if (x[i].classList.contains("highlight")) {
//     // x.style.backgroundColor = "#B10DC9";
//     console.log("hi");
//     onsole.log("length : "+x.classList.contains('highlight'));
//   } else {
//     // x.style.backgroundColor = "#7FDBFF";
//     console.log("bye");

//   }
// }
// var choose = document.querySelector("header");
// choose.classList.style.backgroundColor = "red";

function click() {
  alert("you clicked me !!");
  console.log("you clicked you !!");
}

function hello() {
  console.log("hello");
}

function goodbye() {
  console.log("goodbye");
}

// var helloButton = document.querySelector("#hello");
// helloButton.addEventListener("click", () => {
//   console.log("hello");
// });

// var goodbyeButton = document.querySelector("#goodbye");
// goodbyeButton.addEventListener("click", () => {
//   console.log("goodbye");
// });
const helloButton = document.querySelector("#hello");
helloButton.addEventListener('click',hello);
