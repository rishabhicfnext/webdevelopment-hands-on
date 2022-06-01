const makeRandColor = () => {
  const r = Math.floor(Math.random() * 255);
  const g = Math.floor(Math.random() * 255);
  const b = Math.floor(Math.random() * 255);
  return `rgb(${r}, ${g}, ${b})`;
};

function colorize() {
  this.style.backgroundColor = makeRandColor();
  this.style.color = makeRandColor();
}

const buttons = document.querySelectorAll("button");

for (let button of buttons) {
  button.addEventListener("click", colorize);
}

for (let i = 0; i < 10; i++) {
  console.log("hii");
}
