// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/**
 * Adds a random greeting to the page.
 */
function addRandomGreeting() {
  const greetings = [
    "Hello world!",
    "¡Hola Mundo!",
    "你好，世界！",
    "Bonjour le monde!",
  ];

  const greeting = greetings[Math.floor(Math.random() * greetings.length)];

  const greetingContainer = document.getElementById("greeting-container");
  greetingContainer.innerText = greeting;
}

function addRandomline() {
  const lines = [
    "Im a peacock you gotta let me fly",
    "just do it",
    "crewmen",
    "clap your hands if you believe",
    "FIRE EVERYTHING!",
    "You will be obliterated",
  ];

  const movieline = lines[Math.floor(Math.random() * lines.length)];

  const lineContainer = document.getElementById("line-container");
  lineContainer.innerText = movieline;
}

var i = 0;
var images = [
  "family/bro.jpg",
  "family/cute.jpg",
  "family/fam.jpg",
  "family/fam2.jpg",
  "family/gran.jpg",
  "family/iwon.jpg",
  "family/pa.jpg",
  "family/rain.jpg",
  "family/su.jpg",
  "family/train.jpg",
  "family/tree.jpg",
  "family/treegang.jpg",
  "family/winter.jpg",
  "family/zoom.png",
];
var time = 3000;

function changeImg() {
  document.slide.src = images[i];
  if (i < images.length - 1) {
    i++;
  } else {
    i = 0;
  }
  setTimeout("changeImg()", time);
}

window.onload = changeImg;
async function getRandomQuoteUsingAsyncAwait() {
  const response = await fetch("/data");
  const quote = await response.text();
  document.getElementById("hello-container").innerText = quote;
}

async function getMessages() {
  const response = await fetch("/message");
  const messages = await response.json();
  var text = "";
  for (var i = 0; i < messages.length; i++) {
    text += messages[i];
  }
  console.log(`Messages:${text}`);
  document.getElementById("message-container").innerText = text;
}

async function getComments() {
  const response = await fetch("/comments");
  const comment = await response.json();
  var comments = "";
  for (var i = 0; i < comment.length; i++) {
    comments += "-"+comment[i]+"\n";
  }
  document.getElementById("comment-container").innerText = comments;
}
