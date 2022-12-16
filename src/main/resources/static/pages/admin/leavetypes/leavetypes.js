// 防止在HTML DOM还未加载完成就执行了JS语句
$(document).ready(function () {
  $('[data-toggle="tooltip"]').tooltip();

  $("#toggle-sidebar").click(function () {
    $(".sidebar").toggleClass("hidden");
    $("#toggle-sidebar").toggleClass("fa-angle-double-right");
    $("#toggle-sidebar").toggleClass("fa-ellipsis-h");
    $(".content").toggleClass("col-md-9");
    $(".content").toggleClass("col-md-12");
  });

  $("#edit-apply").click(function () {
    $("#edit-apply").hide();
    $("#save-apply").show();
    $(".department-edit").show();
    $(".department-show").hide();
  });

  $("#save-apply").click(function () {
    $("#edit-apply").show();
    $("#save-apply").hide();
    $(".department-edit").hide();
    $(".department-show").show();
  });
});

$("#delete-apply").click(()=>{
  let id = document.getElementById("leavetype-id").value;
  console.log(id)
  fetch("/api/admin/leave-status/"+id, {
    method:"DELETE",
  }).then(response => response.json()).then((res) => {
    console.log(res);
    if (res.code === 200) {
      window.location.reload();
    } else {
      alert(res.msg)
    }
    // $("#employee-name-edit").val("123" + res.statusText);
  })
})

$("#save-apply").click(()=>{
  let name = document.getElementById("leavetype-name-edit").value;
  let id = document.getElementById("leavetype-id").value;
  console.log(name)
  console.log(id)
  // let url = "http://localhost:8080/api/admin/leave-status/{id}"
  //
  // fetch(url).then(response=>response.json()).then(data=>console.log(data))
  if (id) {
    fetch(`/api/admin/leave-status/`+id, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
            name: name,
          },
      ),
    }).then(response => response.json()).then((res) => {
      console.log(res);
      if (res.code === 200) {
        window.location.reload();
      } else {
        alert(res.msg)
      }
      // $("#employee-name-edit").val("123" + res.statusText);
    })
  } else {
    fetch(`/api/admin/leave-status`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
            name: name,
          },
      ),
    }).then(response => response.json()).then((res) => {
      console.log(res);
      if (res.code === 200) {
        window.location.reload();
      } else {
        alert(res.msg)
      }
      // $("#employee-name-edit").val("123" + res.statusText);
    })
  }

})