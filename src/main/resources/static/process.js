function loadParts(processId) {
    fetch(`/parts/process/${processId}`)
        .then(res => res.text())
        .then(html => {
            document.getElementById("content").innerHTML = html;
        });
}

function loadAllParts() {
    fetch('/parts/all')
        .then(res => res.text())
        .then(html => {
            document.getElementById("content").innerHTML = html;
        });
}

function loadStockSummary(processId) {
    const from = document.querySelector('#from').value;
    const to = document.querySelector('#to').value;

    fetch(`/stock/summary/parts?from=${from}&to=${to}`)
        .then(res => res.text())
        .then(html => {
            document.querySelector('.inventory-area').innerHTML = html;
        });
}

function selectPart(partId, row) {
    // 選択中ハイライト
    document.querySelectorAll(".part-table tr")
        .forEach(tr => tr.classList.remove("selected"));

    row.classList.add("selected");

    // 部品詳細取得
    fetch(`/parts/${partId}`)
        .then(res => res.json())
        .then(part => {
            document.getElementById("partId").value = part.id;
            document.getElementById("partName").value = part.partName;
            document.getElementById("modelNumber").value = part.modelNumber;
            document.getElementById("quantity").value = part.stockQuantity;
            document.getElementById("lastArrivedAt").value = part.lastArrivedAt;

            document.getElementById("editArea").style.display = "block";
        });
}

function createPart() {
    const data = {
        partName: document.getElementById("partName").value,
        modelNumber: document.getElementById("modelNumber").value,
        stockQuantity: document.getElementById("quantity").value,
        lastArrivedAt: document.getElementById("lastArrivedAt").value
    };

    fetch("/parts", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    })
    .then(res => {
        if (!res.ok) {
            alert("登録に失敗しました");
            return;
        }

        alert("登録しました");
        loadAllParts();
    });
}

function updatePart() {
    if (!confirm("この内容で更新しますか？")) return;
    const partId = document.getElementById("partId").value;

    const data = {
        partName: document.getElementById("partName").value,
        modelNumber: document.getElementById("modelNumber").value,
        stockQuantity: document.getElementById("quantity").value,
        lastArrivedAt: document.getElementById("lastArrivedAt").value
    };

    fetch(`/parts/${partId}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    })
    .then(res => {
        if (!res.ok) {
            alert("更新に失敗しました");
            return;
        }

        alert("更新しました");
        loadAllParts();
    });
}

function deletePart() {
    if (!confirm("この部品を削除しますか？")) return;
    const partId = document.getElementById("partId").value;

    fetch(`/parts/${partId}`, {
        method: "DELETE"
    })
    .then(res => {
        if (!res.ok) {
            alert("削除に失敗しました");
            return;
        }

        // 成功時の処理
        alert("削除しました");
        // 編集フォームを隠す
        document.getElementById("editArea").style.display = "none";
        // 一覧を再読み込み
        loadAllParts();
    });
}