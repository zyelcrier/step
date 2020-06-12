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

//Slideshow Function
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

//Servlet
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
  console.log(messages);
  document.getElementById("message-container").innerText = text;
}

async function getComments() {
  const numComments = document.getElementById("user-result").value;
  const response = await fetch("/comments?user-choice=" + numComments);
  const comment = await response.json();
  console.log(comment);
  var comments = "";
  for (var i = 0; i < comment.length; i++) {
    comments += "-"+comment[i].email+": " + comment[i].text + "\n";
  }
  document.getElementById("comment-container").innerText = comments;
}

async function deleteAll() {
  fetch("/delete-data", { method: "POST" });
  window.location.reload();
}

async function loginStatus(){
  const response = await fetch("/login");
  const login = await response.text();
  document.getElementById("login-container").innerHTML = login;
  console.log(response.status);
  var responseStat = response.status;
  if(responseStat>=200 && responseStat<=299){
    document.getElementById("login");
  }else{
    document.getElementById("login").classList.add("hide");
  }  
}

function createMap() {
  const map = new google.maps.Map(document.getElementById('map'),{center: {lat: 29.951065, lng: -90.071533}, zoom: 15});
  const oregonMarker = new google.maps.Marker({ position: {lat: 44.9428975, lng: -123.0350963},map: map,title: 'Where I lived the last 10 years'});
  const bornMarker = new google.maps.Marker({ position: {lat: 29.7604267, lng: -95.3698028},map: map,title: 'Where I was Born'});
  const schoolMarker = new google.maps.Marker({ position: {lat: 29.965219, lng: -90.106994},map: map,title: 'My school'});
  const childHomeMarker = new google.maps.Marker({ position: {lat: 29.95115500973777, lng: -90.12567419408697},map: map,title: 'My Childhood Home'});
  const coastHomeMarker = new google.maps.Marker({ position: {lat: 44.95816440000001, lng: -124.0178914},map: map,title: 'My Favorite place'});
}