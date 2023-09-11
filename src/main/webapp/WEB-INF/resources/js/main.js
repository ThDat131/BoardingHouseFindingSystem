let valueTentantByDay = []
let valueTentantByMonth = []
let valueTentantByQuarter = []
let valueTentantByYear = []

let valueTentantChartByDay = []
let labelTentantChartByDay = []
let valueTentantChartByMonth = []
let labelTentantChartByMonth = []
let valueTentantChartByQuarter = []
let labelTentantChartByQuarter = []
let valueTentantChartByYear = []
let labelTentantChartByYear = []

let valueLandlordByDay = []
let valueLandlordByMonth = []
let valueLandlordByQuarter = []
let valueLandlordByYear = []

let valueLandlordChartByDay = []
let labelLandlordChartByDay = []
let valueLandlordChartByMonth = []
let labelLandlordChartByMonth = []
let valueLandlordChartByQuarter = []
let labelLandlordChartByQuarter = []
let valueLandlordChartByYear = []
let labelLandlordChartByYear = []

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

const chartNumTentant = new Chart(tentantsStats, {
    type: 'bar',
    data: {
        labels: [],
        datasets: [{
            label: 'user',
            data: [],
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
const chartNumLandlord = new Chart(landlordsStats, {
    type: 'bar',
    data: {
        labels: [],
        datasets: [{
            label: 'user',
            data: [],
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

const statsNumTentant = (endpointTentant) => {
    const dateFrom = document.getElementById("yf").value
    const dateTo = document.getElementById("yt").value
    fetch(`${endpointTentant}?f=${dateFrom}&t=${dateTo}`, {
        method: "GET"
    }).then((res) => {
        return res.json()
    }).then((data) => {
        initValueTentant()
        let entries = Object.entries(data)
        entries.map(([key, value]) => {
            if (key === 'statsByDay')
                valueTentantByDay = value
            else if (key === 'statsByMonth')
                valueTentantByMonth = value
            else if (key === 'statsByQuarter')
                valueTentantByQuarter = value
            else if (key === 'statsByYear')
                valueTentantByYear = value
        })
        alert("Lấy dữ liệu thống kê thành công!")
    }).catch(err => alert("Lấy dữ liệu thống kê thất bại!"))
}

const statsNumLandLord = (endpointLandLord) => {
    const dateFrom = document.getElementById("yf").value
    const dateTo = document.getElementById("yt").value
    fetch(`${endpointLandLord}?f=${dateFrom}&t=${dateTo}`, {
        method: "GET"
    }).then((res) => {
        return res.json()
    }).then((data) => {
        initValueLandlord()
        let entries = Object.entries(data)
        entries.map(([key, value]) => {
            if (key === 'statsByDay')
                valueLandlordByDay = value
            else if (key === 'statsByMonth')
                valueLandlordByMonth = value
            else if (key === 'statsByQuarter')
                valueLandlordByQuarter = value
            else if (key === 'statsByYear')
                valueLandlordByYear = value
        })
        alert("Lấy dữ liệu thống kê thành công!")
    }).catch(err => alert("Lấy dữ liệu thống kê thất bại!"))
}

const handleShowUserStas = (tentantEndpoint, landlordEndpoint) => {
    statsNumTentant(tentantEndpoint)
    statsNumLandLord(landlordEndpoint)
}

const generateChart = (type) => {
    if (type === 'day') {
        labelTentantChartByDay = []
        valueTentantChartByDay = []
        labelLandlordChartByDay = []
        valueLandlordChartByDay = []

        valueTentantByDay.map(data => {
            labelTentantChartByDay.push(data[0])
            valueTentantChartByDay.push(data[1])
        })
        chartNumTentant.data.labels = labelTentantChartByDay
        chartNumTentant.data.datasets.forEach(datasets => {
            datasets.data = valueTentantChartByDay
        })

        valueLandlordByDay.map(data => {
            labelLandlordChartByDay.push(data[0])
            valueLandlordChartByDay.push(data[1])
        })
        chartNumLandlord.data.labels = labelLandlordChartByDay
        chartNumLandlord.data.datasets.forEach(datasets => {
            datasets.data = valueLandlordChartByDay
        })

        chartNumTentant.update()
        chartNumLandlord.update()
    }
    if (type === 'month') {
        labelTentantChartByMonth = []
        valueTentantChartByMonth = []
        labelLandlordChartByMonth = []
        valueLandlordChartByMonth = []

        valueTentantByMonth.map(data => {
            labelTentantChartByMonth.push(data[0])
            valueTentantChartByMonth.push(data[1])
        })
        chartNumTentant.data.labels = labelTentantChartByMonth
        chartNumTentant.data.datasets.forEach(datasets => {
            datasets.data = valueTentantChartByMonth
        })

        valueLandlordByMonth.map(data => {
            labelLandlordChartByMonth.push(data[0])
            valueLandlordChartByMonth.push(data[1])
        })
        chartNumLandlord.data.labels = labelLandlordChartByMonth
        chartNumLandlord.data.datasets.forEach(datasets => {
            datasets.data = valueLandlordChartByMonth
        })

        chartNumTentant.update()
        chartNumLandlord.update()
    }
    if (type === 'quarter') {
        labelTentantChartByQuarter = []
        valueTentantChartByQuarter = []
        labelLandlordChartByQuarter = []
        valueLandlordChartByQuarter = []

        valueTentantByQuarter.map(data => {
            labelTentantChartByQuarter.push(data[0])
            valueTentantChartByQuarter.push(data[1])
        })
        chartNumTentant.data.labels = labelTentantChartByQuarter
        chartNumTentant.data.datasets.forEach(datasets => {
            datasets.data = valueTentantChartByQuarter
        })

        valueLandlordByQuarter.map(data => {
            labelLandlordChartByQuarter.push(data[0])
            valueLandlordChartByQuarter.push(data[1])
        })
        chartNumLandlord.data.labels = labelLandlordChartByQuarter
        chartNumLandlord.data.datasets.forEach(datasets => {
            datasets.data = valueLandlordChartByQuarter
        })

        chartNumTentant.update()
        chartNumLandlord.update()
    }
    if (type === 'year') {
        labelTentantChartByYear = []
        valueTentantChartByYear = []
        labelLandlordChartByYear = []
        valueLandlordChartByYear = []

        valueTentantByYear.map(data => {
            labelTentantChartByYear.push(data[0])
            valueTentantChartByYear.push(data[1])
        })
        chartNumTentant.data.labels = labelTentantChartByYear
        chartNumTentant.data.datasets.forEach(datasets => {
            datasets.data = valueTentantChartByYear
        })

        valueLandlordByYear.map(data => {
            labelLandlordChartByYear.push(data[0])
            valueLandlordChartByYear.push(data[1])
        })
        chartNumLandlord.data.labels = labelLandlordChartByYear
        chartNumLandlord.data.datasets.forEach(datasets => {
            datasets.data = valueLandlordChartByYear
        })

        chartNumTentant.update()
        chartNumLandlord.update()
    }
}

const initValueTentant = () => {
    valueTentantByDay = []
    valueTentantByMonth = []
    valueTentantByQuarter = []
    valueTentantByYear = []
}

const initValueLandlord = () => {
    valueLandlordByDay = []
    valueLandlordByMonth = []
    valueLandlordByQuarter = []
    valueLandlordByYear = []
}
