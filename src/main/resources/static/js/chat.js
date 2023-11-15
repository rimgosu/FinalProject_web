// ******   1. css를 위한 기능들      **********
// 1-1. 스크
$(document).ready(function() {
  var chatUl = document.querySelector("#chatting-ul");
  chatUl.scrollTop = chatUl.scrollHeight;
});



// *********   2. 채팅을 위한 기능들      *******

host_address = 'localhost'
var username = '@Session["username"]';

const stompClient = new StompJs.Client({
    brokerURL: 'ws://'+host_address+':8081/boot/gs-guide-websocket'
});

stompClient.onConnect = (frame) => {
    setConnected(true);
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/greetings', (greeting) => {
        showGreeting(JSON.parse(greeting.body).content);
    });
};

stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    createChatRoom()
    stompClient.activate();
}

function disconnect() {
    stompClient.deactivate();
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.publish({
        destination: "/app/hello",
        body: JSON.stringify({'name': $("#name").val()})
    });
}

function showGreeting(message) {
	createChatting(message)
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', (e) => e.preventDefault());
    $( "#connect" ).click(() => connect());
    $( "#disconnect" ).click(() => disconnect());
    $( "#send" ).click(() => sendName());
});

function createChatting(message) {
	$.ajax({
        url: '/boot/CreateChatting',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            room_uuid: "716c67cf-0ec7-40b1-8ced-4ac1f04bfb32", // 프론트엔드에서 UUID 생성 또는 서버에서 생성하도록 둘 수 있음
            chat_uuid: null,
            chat_chatter: null,
            chat_content: message,
            chat_emoticon: null,
            chatted_at: null,
            
        }),
        success: function(response) {
            console.log('채팅 보내기 성공');
        },
        error: function(error) {
            console.log('채팅 보내기 오류', error);
        }
    });
}


// 채팅방 생성 매소드
function createChatRoom() {
    $.ajax({
        url: '/boot/CreateChatRoom',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            room_uuid: null, // 프론트엔드에서 UUID 생성 또는 서버에서 생성하도록 둘 수 있음
            room_describe: "채팅방설명",
            room_joined: "상대방username(구현필요)", // 예시 참여자 목록
            room_maker: username, // 채팅방 생성자
            room_regdate: null, // 현재 시각
            room_status: "open", // 혹은 다른 상태
            room_title: "방이름테스트"
        }),
        success: function(response) {
            console.log('채팅방 생성 성공');
        },
        error: function(error) {
            console.log('채팅방 생성 오류', error);
        }
    });
}











