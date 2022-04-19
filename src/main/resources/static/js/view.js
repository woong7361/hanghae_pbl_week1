/*!
* Start Bootstrap - Small Business v5.0.5 (https://startbootstrap.com/template/small-business)
* Copyright 2013-2022 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-small-business/blob/master/LICENSE)
*/
// This file is intentionally blank
// Use this file to add JavaScript to your project

$(document).ready(function () {
    getBoard()
    getComments()
})

function getBoard() {
    let boardId = window.location.pathname.split('/')[2]
    $.ajax({
            type: "GET",
            url: "/api/board/"+boardId,
            contentType: "application/json",
            data: {},
            success: function (response) {
                let title = response['title']
                let content = response['content']
                let createdBy = response['createdBy']
                let createdAt = response['createdAt']

                temp_html =
                    `
                    <div class="row gx-4 gx-lg-5 align-items-center my-5">
                        <div class="col-lg-5">
                            <h1 class="font-weight-light">${title}</h1>
                            <p>${content}</p>
                            <p>${createdBy}</p>
                            <p>${createdAt}</p>
                            <a class="btn btn-primary" onClick="patchBoard(${boardId})">edit board</a>
                            <a class="btn btn-primary" onClick="deleteBoard(${boardId})">delete board</a>
                        </div>
                    </div>
                `
                console.log(response)
               $('#board').append(temp_html)

            }
        });
}
function getComments() {
    let boardId = window.location.pathname.split('/')[2]
    $.ajax({
            type: "GET",
            url: "/api/board/"+boardId+"/comment",
            contentType: "application/json",
            data: {},
            success: function (response) {
                for (const comment of response['comments']) {
                    let content = comment['content']
                    let commentId = comment['id']
                    console.log(comment)
                    let temp_html =
                        `
                        <div class="col-md-4 mb-5">
                            <div class="card h-100">
                                <div class="card-body">
                                    <p class="card-text">${content}</p>
                                </div>                              
                                <div class="card-footer"><a class="btn btn-primary btn-sm" onclick="patchComment(${commentId})">edit comment</a></div>
                                <div class="card-footer"><a class="btn btn-primary btn-sm" onclick="deleteComment(${commentId})">delete commnet</a></div>
                            </div>
                        </div>
                    `
                    $('#comment').append(temp_html)
                }
            }
        });
}

function deleteBoard(boardId) {
    $.ajax({
        type: "POST",
        url: "/api/board/"+ boardId +"/delete",
        contentType: "application/json",
        data: {},
        success: function (response) {
            alert("delete Success")
            document.location.href = '/';
        }
    });
}
function patchBoard(boardId) {
    let title = prompt("write title here");
    if (title === ""){title = null}
    let createdBy = prompt("write createdBy here");
    if (createdBy === ""){createdBy = null}
    let content = prompt("write content here");
    if (content === ""){content = null}
    $.ajax({
        type: "POST",
        url: "/api/board/"+ boardId +"/patch",
        contentType: "application/json",
        data: JSON.stringify({"title": title, "createdBy": createdBy, "content": content}),
        success: function (response) {
            alert("edit Success")
            document.location.href = '/view/'+boardId;
        }
    });
}
function deleteComment(commentId) {
    let boardId = window.location.pathname.split('/')[2]
    $.ajax({
        type: "POST",
        url: "/api/board/"+ boardId +"/comment/"+commentId+"/delete",
        contentType: "application/json",
        data: {},
        success: function (response) {
            alert("delete Success")
            document.location.href = '/view/'+boardId;
        }
    });
}
function patchComment(commentId) {
    let boardId = window.location.pathname.split('/')[2]
    let content = prompt("write comment content here");
    if (content === ""){content = null}
    $.ajax({
        type: "POST",
        url: "/api/board/"+ boardId +"/comment/"+commentId+"/patch",
        contentType: "application/json",
        data: JSON.stringify({"content": content}),
        success: function (response) {
            alert("edit Success")
            document.location.href = '/view/'+boardId;
        }
    });
}
function newComment() {
    let boardId = window.location.pathname.split('/')[2]
    let content = prompt("write new comment content here");
    if (content === ""){content = null}
    $.ajax({
        type: "POST",
        url: "/api/board/"+ boardId +"/comment",
        contentType: "application/json",
        data: JSON.stringify({"content": content}),
        success: function (response) {
            if (response['msg'] == 'fail') {
                alert("write content please")
            }
            else{
                alert("create Success");
            }
            document.location.href = '/view/'+boardId;
        }
    });
}