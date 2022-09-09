$(document).ready(function(){ 
    $("#refresh").click(function(){ 
        $.ajax({ 
            type: "GET",    
            url: "connDB.php",
            dataType: "json",
            success: function(data) {
                if (data.success) { 
                    $("#searchResult_t").html(data.temperature);
                    $("#searchResult_i").html(data.illumination);
                    $("#searchResult_p").html(data.pm);
                    $("#searchResult_c").html(data.co);
                } else {
                    $("#searchResult_t").html("出现错误：" + data);
                }  
            },
            error: function(jqXHR){     
               alert("发生错误：" + jqXHR.status);  
            },     
        });
    });
})