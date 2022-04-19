/*!
* Start Bootstrap - Shop Homepage v5.0.5 (https://startbootstrap.com/template/shop-homepage)
* Copyright 2013-2022 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-shop-homepage/blob/master/LICENSE)
*/
// This file is intentionally blank
// Use this file to add JavaScript to your project

$(document).ready(function () {
    getBoard()
})

function getBoard() {
    $.ajax({
        type: "GET",
        url: "/api/board/boards",
        contentType: "application/json",
        data: {},
        success: function (response) {
            for (const board of response) {
                title = board['title']
                createdAt = board['createdAt']
                createdBy = board['createdBy']
                boardId = board['id']
                temp_html =
                `
                    <div class="card h-100">
                        <!-- Product image-->
                        <img class="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..." />
                        <!-- Product details-->
                        <div class="card-body p-4">
                            <div class="text-center">
                                <!-- Product name-->
                                <h5 class="fw-bolder">${title}</h5>
                                <!-- Product price-->
                                ${createdAt}
                            </div>
                        </div>
                        <!-- Product actions-->
                        <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                            <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="/view/${boardId}">${createdBy}</a></div>
                        </div>
                    </div>
                `

                $('#board').append(temp_html)
            }
//            alert('메시지가 성공적으로 작성되었습니다.');
//            window.location.reload();
        }
    });
}
function newBoard() {
    let title = prompt("write new Board title here");
    if (title === ""){title = null}
    let content = prompt("write new Board content here");
    if (content === ""){content = null}
    let createdBy = prompt("write new Board createdBy here");
    if (createdBy === ""){createdBy = null}
    $.ajax({
        type: "POST",
        url: "/api/board/",
        contentType: "application/json",
        data: JSON.stringify({"title": title,"content": content,"createdBy": createdBy}),
        success: function (response) {
            alert("create Success")
            document.location.href = '/';
        }
    });
}