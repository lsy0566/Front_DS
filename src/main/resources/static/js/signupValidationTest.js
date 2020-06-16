function Validation() {

    var RegExp = /^[가-힣a-zA-Z0-9]{2,12}$/; // 아이디 유효성 검사
    var PwExp = /^[a-zA-Z0-9]{6,12}$/; // 비밀번호 유효성 검사
    var EmailExp = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/; // 이메일 유효성 검사
    var PhoneExp = /^[0-9]{9,11}$/;//전화번호 유효성 검사
    // var nameExp = /^[가-힣]{2,4}|[a-zA-Z]{2,10}\s[a-zA-Z]{2,10}$/; // 한글이름이나 영어 유효성 검사

    var userId = document.getElementById("valid_username");
    var userPs = document.getElementById("valid_password");
    var userM = document.getElementById("valid_email");

    var userPhone = document.getElementById("valid_phoneNumber");


//===========================================================
// 이메일이 입력되지 않을 경우
    if (userM.value == "") {
        alert("이메일을 입력해주세요.");
        return false;
    }
// 이메일이 이메일 형식과 맞지 않을 경우
    if (EmailExp.test(userM.value) == false) {
        alert("이메일형식이 맞지 않습니다.");
        return false;
    }

//==============================================================
// 아이디가 입력되지 않은 경우
    if (userId.value == "") {
        alert("이름을 입력해주세요.");
        return false;
    }

// 아이디가 2~12자리 영문대소문자와 숫자로만 입력
    if (!RegExp.test(userId.value)) {
        alert("이름을 2~12자리 영문대소문자와 숫자로만 입력해주세요");
        return false;
    }

//id와 password가 같을 경우
    if (userId.value == userPs.value) {
        alert("id와 password가 같습니다.");
        return false;
    }


//==============================================================
//password가 입력되지 않은 경우
    if (userPs.value == "") {
        alert("비밀번호를 입력해주세요.");
        return false;
    }

//password 6~12자리 영문대소문자와 숫자로만 입력
    if (!PwExp.test(userPs.value)) {
        alert("비밀번호를 6~12자리 영문대소문자와 숫자로만 입력해주세요.");
        return false;
    }

//id와 password_check가 같을 경우
    if (userId.value == userPs.value) {
        alert("아이디와 비밀번호가 같습니다.");
        return false;
    }

//==============================================================
//phoneNumber가 입력되지 않을 경우
    if (userPhone.value == "") {
        alert("전화번호를 입력해주세요.");
        return false;
    }
//password_check 6~12자리 영문대소문자와 숫자로만 입력
    if (!PhoneExp.test(userPhone.value)) {
        alert("전화번호를 9~11자리 숫자로만 입력해주세요.");
        return false;
    }
}