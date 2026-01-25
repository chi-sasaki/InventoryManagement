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