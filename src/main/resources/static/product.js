function searchProduct(event) {
    event.preventDefault();

    const companyId = document.querySelector("[name='companyId']").value;

    fetch(`/product/search?companyId=${companyId}`)
        .then(res => res.text())
        .then(html => {
            document.getElementById("content").innerHTML = html;
        });
}

function loadProduct(productId) {
    fetch(`/product/${productId}/edit`)
        .then(res => res.text())
        .then(html => {
            document.getElementById("content").innerHTML = html;
        });
}

function loadAllProducts() {
    fetch('/product')
        .then(res => res.text())
        .then(html => {
            document.getElementById("content").innerHTML = html;
        });
}

function orderByName(sort){
    fetch(`/product/orderByName?sort=${sort}`)
        .then(res => res.text())
        .then(html => {
            document.getElementById("content").innerHTML = html;
        });
}

function orderByStock(sort){
    fetch(`/product/orderByStock?sort=${sort}`)
        .then(res => res.text())
        .then(html => {
            document.getElementById("content").innerHTML = html;
        });
}

function lastOrdered(sort){
    fetch(`/product/lastOrdered?sort=${sort}`)
        .then(res => res.text())
        .then(html => {
            document.getElementById("content").innerHTML = html;
        });
}

function registerProduct(event) {
    event.preventDefault();

    const formData = new FormData(event.target);

    fetch("/register/product", {
        method: "POST",
        body: formData
    })
    .then(res => res.text())
    .then(html => {
        document.getElementById("content").innerHTML = html;
    });
}

function updateProduct() {

    if (!confirm("この内容で更新しますか？")) return;

    const formData = new FormData();

    formData.append("id", document.getElementById("productId").value);
    formData.append("companyId", document.getElementById("companyId").value);
    formData.append("productName", document.getElementById("productName").value);
    formData.append("modelNumber", document.getElementById("modelNumber").value);
    formData.append("stockQuantity", document.getElementById("quantity").value);
    formData.append("lastOrderedAt", document.getElementById("lastOrderedAt").value);

    fetch("/product/update", {
        method: "POST",
        body: formData
    })
    .then(res => res.text())
    .then(html => {
        document.getElementById("content").innerHTML = html;
    });
}

function deleteProducts() {

    if (!confirm("選択した製品を削除しますか？")) return;

    const formData = new FormData();

    document.querySelectorAll("input[name='deleteIds']:checked")
        .forEach(cb => formData.append("deleteIds", cb.value));

    fetch("/product/bulk-delete", {
        method: "POST",
        body: formData
    })
    .then(res => res.text())
    .then(html => {
        document.getElementById("content").innerHTML = html;
    });
}