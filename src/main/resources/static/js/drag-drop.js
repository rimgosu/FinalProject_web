// 요소를 드래그하기 위한 로직
dragElement(document.getElementById("mydiv"));

function dragElement(elmnt) {
    var pos1 = 0, pos2 = 0, pos3 = 0, pos4 = 0;
    if (document.getElementById(elmnt.id + "header")) {
        // 헤더가 있는 경우 헤더에서 드래그
        document.getElementById(elmnt.id + "header").onmousedown = dragMouseDown;
    } else {
        // 그렇지 않으면 요소 자체에서 드래그
        elmnt.onmousedown = dragMouseDown;
    }

    // 이미지에서 드래그 시작 설정
    document.getElementById("myimage").onmousedown = function(e) {
        e.preventDefault();
        dragMouseDown(e);
    }

    function dragMouseDown(e) {
        e = e || window.event;
        e.preventDefault();
        // 마우스 커서 위치를 시작 위치로 설정
        pos3 = e.clientX;
        pos4 = e.clientY;
        document.onmouseup = closeDragElement;
        // 마우스 커서가 움직일 때 요소를 움직임
        document.onmousemove = elementDrag;
    }

    function elementDrag(e) {
        e = e || window.event;
        e.preventDefault();
        // 마우스의 새 위치 계산
        pos1 = pos3 - e.clientX;
        pos2 = pos4 - e.clientY;
        pos3 = e.clientX;
        pos4 = e.clientY;
        // 요소의 새 위치 설정
        elmnt.style.top = (elmnt.offsetTop - pos2) + "px";
        elmnt.style.left = (elmnt.offsetLeft - pos1) + "px";
    }

    function closeDragElement() {
        // 드래그 중지
        document.onmouseup = null;
        document.onmousemove = null;
    }
}