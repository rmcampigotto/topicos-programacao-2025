const canvas = document.getElementById("pingpongCanvas");
const ctx = canvas.getContext("2d");

// Definir as dimensões do canvas
canvas.width = 800;
canvas.height = 400;

// Variáveis para o jogo
let leftPaddle = {
  x: 30,
  y: canvas.height / 2 - 40,
  width: 10,
  height: 80,
  dy: 0
};

let rightPaddle = {
  x: canvas.width - 40,
  y: canvas.height / 2 - 40,
  width: 10,
  height: 80,
  dy: 0
};

let ball = {
  x: canvas.width / 2,
  y: canvas.height / 2,
  radius: 10,
  dx: 4,
  dy: 4
};

// Pontuação
let leftScore = 0;
let rightScore = 0;

// Desenhar os paddles e a bola
function draw() {
  ctx.clearRect(0, 0, canvas.width, canvas.height);

  // Desenho do paddle esquerdo
  ctx.fillStyle = "white";
  ctx.fillRect(leftPaddle.x, leftPaddle.y, leftPaddle.width, leftPaddle.height);

  // Desenho do paddle direito
  ctx.fillRect(rightPaddle.x, rightPaddle.y, rightPaddle.width, rightPaddle.height);

  // Desenho da bola
  ctx.beginPath();
  ctx.arc(ball.x, ball.y, ball.radius, 0, Math.PI * 2);
  ctx.fill();
  ctx.closePath();

  // Desenho da pontuação
  ctx.font = "24px Arial";
  ctx.fillText(`Jogador 1: ${leftScore}`, 50, 30);
  ctx.fillText(`Jogador 2: ${rightScore}`, canvas.width - 150, 30);

  // Atualizar as posições da bola
  ball.x += ball.dx;
  ball.y += ball.dy;

  // Colisões com o topo e o fundo
  if (ball.y - ball.radius <= 0 || ball.y + ball.radius >= canvas.height) {
    ball.dy = -ball.dy;
  }

  // Colisão com os paddles
  if (ball.x - ball.radius <= leftPaddle.x + leftPaddle.width &&
      ball.y >= leftPaddle.y && 
      ball.y <= leftPaddle.y + leftPaddle.height) {
    ball.dx = -ball.dx;
    // Pequena variação para um efeito de angulação na bola
    let angle = (ball.y - (leftPaddle.y + leftPaddle.height / 2)) / (leftPaddle.height / 2);
    ball.dy = angle * 4;
  }

  if (ball.x + ball.radius >= rightPaddle.x &&
      ball.y >= rightPaddle.y && 
      ball.y <= rightPaddle.y + rightPaddle.height) {
    ball.dx = -ball.dx;
    // Pequena variação para um efeito de angulação na bola
    let angle = (ball.y - (rightPaddle.y + rightPaddle.height / 2)) / (rightPaddle.height / 2);
    ball.dy = angle * 4;
  }

  // Se a bola ultrapassar a tela, reiniciar a posição e pontuar
  if (ball.x - ball.radius <= 0) {
    rightScore++; // Pontuação para o jogador da direita
    resetBall();
  }

  if (ball.x + ball.radius >= canvas.width) {
    leftScore++; // Pontuação para o jogador da esquerda
    resetBall();
  }

  // Atualizar a posição dos paddles
  leftPaddle.y += leftPaddle.dy;
  rightPaddle.y += rightPaddle.dy;

  // Prevenir os paddles de saírem da tela
  leftPaddle.y = Math.max(Math.min(leftPaddle.y, canvas.height - leftPaddle.height), 0);
  rightPaddle.y = Math.max(Math.min(rightPaddle.y, canvas.height - rightPaddle.height), 0);
}

// Função para resetar a bola
function resetBall() {
  ball.x = canvas.width / 2;
  ball.y = canvas.height / 2;
  ball.dx = -ball.dx;
  ball.dy = 4;
}

// Movimentação dos paddles (usando teclas)
document.addEventListener("keydown", (event) => {
  if (event.key === "ArrowUp") {
    rightPaddle.dy = -6;
  }
  if (event.key === "ArrowDown") {
    rightPaddle.dy = 6;
  }
  if (event.key === "w") {
    leftPaddle.dy = -6;
  }
  if (event.key === "s") {
    leftPaddle.dy = 6;
  }
});

document.addEventListener("keyup", (event) => {
  if (event.key === "ArrowUp" || event.key === "ArrowDown") {
    rightPaddle.dy = 0;
  }
  if (event.key === "w" || event.key === "s") {
    leftPaddle.dy = 0;
  }
});

// Função de animação
function gameLoop() {
  draw();
  requestAnimationFrame(gameLoop);
}

// Iniciar o jogo
gameLoop();
