function loadAllParts() {
    fetch('/parts/all')
        .then(res => res.text())
        .then(html => {
            document.getElementById("content").innerHTML = html;
        });
}

function loadStockSummary() {
    const from = document.querySelector('#from').value;
    const to = document.querySelector('#to').value;

    fetch(`/stock/summary/parts?from=${from}&to=${to}`)
        .then(res => res.text())
        .then(html => {
            document.querySelector('.summary-area').innerHTML = html;
        });
}

function loadPart(partId) {
    fetch(`/parts/${partId}/edit`)
        .then(res => res.text())
        .then(html => {
            document.getElementById("content").innerHTML = html;
        });
}

function searchPart(e) {
    e.preventDefault();
    const form = e.target;
    const params = new URLSearchParams(new FormData(form));
    fetch(`/parts/search?${params}`)
        .then(res => res.text())
        .then(html => {
            document.getElementById("content").innerHTML = html;
        });
}

function registerPart(e) {
    e.preventDefault();

    const formData = new FormData(e.target);

    fetch("/parts/register", {
        method: "POST",
        body: formData
    })
    .then(res => res.text())
    .then(html => {
        document.getElementById("content").innerHTML = html;
    });
}

function updatePart() {

    if (!confirm("この内容で更新しますか？")) return;

    const formData = new FormData();

    formData.append("id", document.getElementById("partId").value);
    formData.append("processId",
        document.getElementById("processId").value);
    formData.append("partName", document.getElementById("partName").value);
    formData.append("modelNumber", document.getElementById("modelNumber").value);
    formData.append("stockQuantity", document.getElementById("stockQuantity").value);
    formData.append("lastOrderedAt", document.getElementById("lastOrderedAt").value);

    fetch("/parts/update", {
        method: "POST",
        body: formData
    })
    .then(res => res.text())
    .then(html => {
        document.getElementById("content").innerHTML = html;
    });
}

function deleteParts() {

    if (!confirm("選択した部品を削除します。よろしいですか？")) return;

    const checked = document.querySelectorAll(
        'input[name="deleteIds"]:checked'
    );

    const formData = new FormData();

    checked.forEach(c => formData.append("deleteIds", c.value));

    fetch("/parts/delete", {
        method: "POST",
        body: formData
    })
    .then(res => res.text())
    .then(html => {
        document.getElementById("content").innerHTML = html;
    });
}