function convertSql2Obj() {
    var input=$("#txt_content").val();
    if(input==""){
        alert("输入不能为空!");
        return;
    }
    $.ajax({
           type:"POST",
           url:"convert/sqlToObj",
           dataType:"json",
           data:{content:$("#txt_content").val(),operation:$("#sel_operation").val()},
           success:function(result){
               $("#txt_output").html("");
               $("#txt_output").append("public class "+result.data.objectName+" {\n");
               for(var i=0;i<result.data.lines.length;i++){
                   $("#txt_output").append("    // "+result.data.lines[i].comment+"\n");
                   $("#txt_output").append("    private "+result.data.lines[i].type+" "+result.data.lines[i].name+";\n");
               }
               $("#txt_output").append("}\n");
           },
           error:function(jqXHR){
               $("#txt_output").html("发生错误:"+jqXHR.status);
           }
       });
}

function clearAll() {
    $("#txt_output").html("");
    $("#txt_content").html("");
}