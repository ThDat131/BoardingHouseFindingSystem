
const loadAllProvinces = async () => {
    const res = await fetch('http://localhost:8080/BoardingHouseFindingSystem/api/provinces')
    return await res.json()
}

const loadAllDistrictsByProvinceCode = async(code) => {
    const res = await fetch(`http://localhost:8080/BoardingHouseFindingSystem/api/province/${code}/districts`)
    return await res.json()
}

const loadAllWardssByDistrictCode = async (code) => {
    const res = await fetch(`http://localhost:8080/BoardingHouseFindingSystem/api/district/${code}/wards`)
    return await res.json()
}

export { loadAllProvinces, loadAllDistrictsByProvinceCode, loadAllWardssByDistrictCode }