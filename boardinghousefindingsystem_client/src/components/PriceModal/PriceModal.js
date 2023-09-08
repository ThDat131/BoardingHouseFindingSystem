import { useState } from 'react'
import './PriceModal.css'
const PriceModal = ({isOpen, onClose, priceValue}) => {

    const [minPrice, setMinPrice] = useState(0)
    const [maxPrice, setMaxPrice] = useState(0)

    const handleMinPriceSelected = (event) => {
        setMinPrice(event.target.value)
    }

    const handleMaxPriceSelected = (event) => {
        setMaxPrice(event.target.value)
    }

    const submitValue  = () => {

        const inputMinPrice = document.getElementById('priceMinRange')
        const inputMaxPrice = document.getElementById('priceMaxRange')

        if (minPrice > maxPrice){
            alert(minPrice + '-' + maxPrice + '???')
            setMinPrice(0)
            setMaxPrice(0)
            inputMinPrice.value=0
            inputMaxPrice.value=0
            return;
        }
        priceValue({
            "fromPrice": minPrice,
            "toPrice": maxPrice
        })
        onClose()
    }

    return <>
        <div className={`modal fade show ${isOpen ? 'showUp' : ''}`} tabIndex="-1">
            <div className="modal-dialog">
                <div className="modal-content">
                    <div className="modal-header">
                        <h5 className="modal-title">Chọn giá</h5>
                        <button type="button" className="btn-close" onClick={onClose}></button>
                    </div>
                    <div className="modal-body">
                        <label htmlFor="priceMinRange" className="form-label">Giá thấp nhất: {minPrice}</label>
                        <input type="range" className="form-range" id="priceMinRange" min={0} max={10000000} 
                            onChange={handleMinPriceSelected} step={100000}/>
                        <label htmlFor="priceMaxRange" className="form-label">Giá cao nhất: {maxPrice}</label>
                        <input type="range" className="form-range" id="priceMaxRange" min={0} max={10000000}
                            onChange={handleMaxPriceSelected} step={100000} />
                    </div>
                    <div className="modal-footer">
                        <button type="button" className="btn btn-secondary" onClick={onClose}>Close</button>
                        <button type="button" className="btn btn-primary" onClick={submitValue}>Save changes</button>
                    </div>
                </div>
            </div>
        </div>
    </>
}

export default PriceModal