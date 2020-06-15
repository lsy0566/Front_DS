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

//    var userName = [[${userName}]];     // 원래는 data에 userName을 같이 보내기 위함
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

    $("#btnSubmit").prop("disabled", true);


    $.ajax({
            type: "POST",
            enctype: 'multipart/form-data',
            url: "http://django-app-service:8083/api/upload/",       // "/api/upload/multi"          => 다른 서버로 보낼때 http:// 를 명시해줘야함 //  http://localhost:8000/api/upload
            data: data,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,

           complete: function () {
                alert("파일 업로드 성공")
               location.replace('/mypageFilelist')
          }
        });
}