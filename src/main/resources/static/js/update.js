

$(function()
{
   $("#btnUpdate").click(function(){
   var dataArrayToSend = [];
   var dataArrayToSend1 = [];

   $("#testTable tr").each(function(){

   var len = $(this).find("td").length;

   for(var i = 0; i<len; i++) {
   dataArrayToSend.push($(this).find("td").eq(i).text());
   }
   dataArrayToSend1.push(dataArrayToSend);
   });

    $.ajax({
    contentType:"application/json",
    type:"POST",
    data:JSON.stringify(dataArrayToSend1),
    url:"${pageContext.request.contextPath}/table/tabledataSend",
    success:function(data){
    console.log('done');
    },
    error:function(jqXHR, textStatus, errorThrown){
    console.log('error while post');
    }


    });

   });
});

