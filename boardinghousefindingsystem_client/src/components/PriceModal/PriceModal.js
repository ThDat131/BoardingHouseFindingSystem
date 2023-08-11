import { useState } from 'react'
import './PriceModal.css'
const PriceModal = ({isOpen, onClose, minPriceValue, maxPriceValue}) => {

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
        minPriceValue(minPrice)
        maxPriceValue(maxPrice)
        onClose()
    }

    return <>
        <div class={`modal fade show ${isOpen ? 'showUp' : ''}`} tabIndex="-1">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Chọn giá</h5>
                        <button type="button" class="btn-close" onClick={onClose}></button>
                    </div>
                    <div class="modal-body">
                        <label htmlFor="priceMinRange" class="form-label">Giá thấp nhất: {minPrice}</label>
                        <input type="range" class="form-range" id="priceMinRange" min={0} max={10000000} 
                            onChange={handleMinPriceSelected} step={500000}/>
                        <label htmlFor="priceMaxRange" class="form-label">Giá cao nhất: {maxPrice}</label>
                        <input type="range" class="form-range" id="priceMaxRange" min={0} max={10000000}
                            onChange={handleMaxPriceSelected} step={500000} />
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" onClick={onClose}>Close</button>
                        <button type="button" class="btn btn-primary" onClick={submitValue}>Save changes</button>
                    </div>
                </div>
            </div>
        </div>
    </>
}

export default PriceModal