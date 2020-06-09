$(document).ready(function () {

    $("#btnSubmit").click(function (event) {
        // form submit 멈추기, 수동적으로 post 보내기 위함
        event.preventDefault();

        fire_ajax_submit();

    });

});

function fire_ajax_submit() {

    // form 가져오기
    var form = $('#fileUploadForm')[0]; // 여기서 id값도 같이 가져와야 하나?
    console.log(form);
    var data = new FormData(form);

    var id_check = $(this).attr("userName");    // userName 이라는 id값 가져옴
    var userName = $('input[name=userName]').val(); // input값의 name의 value값을 가져옴
            console.log("-------------");
//            console.log(value);
            console.log("userName : ", userName);
            console.log("-------------");

//    var userName = [[${userName}]];     // data에 userName을 같이 보내기 위함
//    console.log(userName)
//    console.log("데이터 : "+ data);
//    console.log(data.userName);
    if(data != null){
    console.log("FORM DATA  EXISTS")
    }else{
        console.log("form is null");
        alert("업로드 할 파일을 선택해주세요.");
    }


    console.log(data);
    console.log("데이터 출력 끝! ");

    data.append("CustomField", "여기는 여분 데이터 테스팅");
    data.append("userName", userName);  // 파일과 userName을 같이 보내기 위함

// data.append("파일 업로드 하는 user의 id", "id 값");  id값 추가시켜서 같이 보내야함
    $("#btnSubmit").prop("disabled", true);

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/api/upload/multi",       // "/api/upload/multi"          => 다른 서버로 보낼때 http:// 를 명시해줘야함 //  http://localhost:8000/api/upload
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data, xhr, textHttp) {
        // 데이터 잘 전송됬는지 상태값에 따라 alert 띄워 줘야함

        $("#result").text(data);
        console.log("성공 : ", data);
        $("#btnSubmit").prop("disabled", false);

        },
        error: function(e) {

            $("#result").text(e.responseText);
            console.log("ERROR : ", e);
            $("#btnSubmit").prop("disabled", false);
        alert("파일 업로드에 실패하였습니다.");
        }
    });
}