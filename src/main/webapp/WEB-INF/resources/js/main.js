const activateUser = (endpoint) => {
    fetch(endpoint, {
        method: "PUT"
    }).then(res => {
        if(res.status === 200)
            location.reload()
        else alert("Something has wrong")
    })
}