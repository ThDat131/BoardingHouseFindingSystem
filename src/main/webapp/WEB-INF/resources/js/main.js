const activateUser = (endpoint) => {
    fetch(endpoint, {
        method: "PUT"
    }).then(res => {
        if(res.status === 200)
            location.reload()
        else alert("Something has wrong")
    })
}

const tentantsStats = document.getElementById('tentantsStats');
const landlordsStats = document.getElementById('landlordsStats');
let chartNumTentant = null
let chartNumLandLord = null

const statsNumTentant = (endpointTentant) => {
    let label = []
    let value = []

    if (chartNumTentant !== null && chartNumTentant.context !== null) {
        chartNumTentant.destroy()
        label = []
        value = []
    }
    const dateFrom = document.getElementById("yf").value+"-01-01"
    const dateTo = document.getElementById("yt").value+"-01-01"
    fetch(`${endpointTentant}?f=${dateFrom}&t=${dateTo}`, {
        method: "GET"
    }).then((res) => {
        return res.json()
    }).then((data) => {
        data.forEach((item) => {
            label.push(item[0])
            value.push(item[1])
        })
        chartNumTentant = new Chart(tentantsStats, {
            type: 'bar',
            data: {
                labels: label,
                datasets: [{
                    label: 'user',
                    data: value,
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    })
}

const statsNumLandLord = (endpointLandLord) => {
    let label = []
    let value = []

    if (chartNumLandLord !== null && chartNumLandLord.context !== null) {
        chartNumLandLord.destroy()
        label = []
        value = []
    }
    const dateFrom = document.getElementById("yf").value+"-01-01"
    const dateTo = document.getElementById("yt").value+"-01-01"
    fetch(`${endpointLandLord}?f=${dateFrom}&t=${dateTo}`, {
        method: "GET"
    }).then((res) => {
        return res.json()
    }).then((data) => {
        data.forEach((item) => {
            label.push(item[0])
            value.push(item[1])
        })
        chartNumLandLord = new Chart(landlordsStats, {
            type: 'bar',
            data: {
                labels: label,
                datasets: [{
                    label: 'user',
                    data: value,
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    })
}

const handleShowUserStas = (tentantEndpoint, landlordEndpoint) => {
    statsNumTentant(tentantEndpoint)
    statsNumLandLord(landlordEndpoint)
}
