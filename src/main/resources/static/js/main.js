$(document).ready(function () {

    $("#btnSubmit").click(function (event) {
        // form submit 멈추기, 수동적으로 post 보내기 위함
        event.preventDefault();

        fire_ajax_submit();

    });

});

function fire_ajax_submit() {

    // form 가져오기
    var form = $('#fileUploadForm')[0];
    console.log(form);
    var data = new FormData(form);
//    let temp = document.getElementById("fileUpload");
//    let data = new FormData(temp);
    console.log("데이터 : "+ data);
    if(!data){
    console.log("FORM DATA  EXISTS")
    }else{
        console.log("form is null");
    }


    console.log(data);
    console.log("데이터 출력 끝! ");
    data.append("CustomField", "여기는 여분 데이터 테스팅")

    $("#btnSubmit").prop("disabled", true);

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/api/upload/multi",       // "/api/upload/multi"          => 다른 서버로 보낼때 http:// 를 명시해줘야함
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {

        $("#result").text(data);
        console.log("성공 : ", data);
        $("#btnSubmit").prop("disabled", false);

        },
        error: function(e) {

            $("#result").text(e.responseText);
            console.log("ERROR : ", e);
            $("#btnSubmit").prop("disabled", false);

        }
    });
}