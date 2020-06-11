function Validation() {

    var userId = document.getElementById("valid_username");
    var userPs = document.getElementById("valid_password");

//==============================================================
// 아이디가 입력되지 않은 경우
    if (userId.value == "") {
        alert("이름을 입력해주세요.");
        return false;
    }

//==============================================================
//password가 입력되지 않은 경우
    if (userPs.value == "") {
        alert("비밀번호를 입력해주세요.");
        return false;
    }
}