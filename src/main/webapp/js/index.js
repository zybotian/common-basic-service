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
               $("#txt_object_name").val(result.data.objectName);
               $("#txt_output").append("public class "+result.data.objectName+" {\n");
               for(var i=0;i<result.data.lines.length;i++){
                   // comment允许没有
                   if (result.data.lines[i].comment!="") {
                       $("#txt_output").append("    // "+result.data.lines[i].comment+"\n");
                   }
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

function download() {
    var url = "file/download";
    var fileName = $("#txt_object_name").val();
    var fileContent = $("#txt_output").val();
    if (fileName=="" || fileContent==""){
        alert("请先解析输入后再下载解析结果！");
        return;
    }
    // 加上文件后缀名，暂只支持sql转java
    fileName += ".java";
    var form = $("<form></form>").attr("action", url).attr("method", "post");
    form.append($("<input></input>").attr("type", "hidden").attr("name", "fileName").attr("value", fileName));
    form.append($("<input></input>").attr("type", "hidden").attr("name", "fileContent").attr("value", fileContent));
    form.appendTo('body').submit().remove();
}