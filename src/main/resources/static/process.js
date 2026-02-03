document.addEventListener("DOMContentLoaded", () => {
    // 左メニューのリンクを取得
    document.querySelectorAll(".process-link").forEach(link => {
        link.addEventListener("click", function(event) {
            event.preventDefault(); // ページ遷移を止める
            const processId = Number(this.dataset.id);

            let url;
            if (processId === 3) {
                // 工程3 → 部品一覧
                url = `/parts/process/${processId}`;
            } else if (processId === 9) {
                // 工程9 → 製品一覧
                url = `/products/process/${processId}`;
            } else {
                return;
            }

            fetch(url)
                .then(res => {
                    if (!res.ok) throw new Error("Network response was not ok");
                    return res.text();
                })
                .then(html => {
                    document.getElementById("content").innerHTML = html;
                })
                .catch(err => {
                    console.error(err);
                    alert("ページの読み込みに失敗しました");
                });
        });
    });
});