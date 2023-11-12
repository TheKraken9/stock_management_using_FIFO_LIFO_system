import React, {useEffect, useState} from 'react';
import 'tailwindcss/tailwind.css';

const Form = () => {
    const [article, setArticle] = useState('');
    const [quantity, setQuantity] = useState('');
    const [unit_price, setUnit_price] = useState('');
    const [date, setDate] = useState('');
    const [store, setStore] = useState('');
    const [items, setItems] = useState([]);
    const [stores, setStores] = useState([]);

    const [messageItem, setMessageItem] = useState('');
    const [infoItem, setInfoItem] = useState('');
    const [errorItem, setErrorItem] = useState('');

    const [messageStore, setMessageStore] = useState('');
    const [infoStore, setInfoStore] = useState('');
    const [errorStore, setErrorStore] = useState('');

    const [message, setMessage] = useState('');
    const [status_code, setStatusCode] = useState('');

    useEffect(() => {
        getItem();
        getStore();
    }, []);


    const getItem = () => {
        fetch('http://localhost:8080/stock_fifo_lifo-1.0-SNAPSHOT/api/items/std')
            .then((response) => response.json())
            .then((data) => {
                console.log('Success:', data);
                setItems(data.items);
                setMessageItem(data.message);
                setInfoItem(data.info);
            })
            .catch((error) => {
                console.error('Error:', error);
            });
    }

    /*setItems(items.items);
    console.log(items);*/

    const getStore = () => {
        fetch('http://localhost:8080/stock_fifo_lifo-1.0-SNAPSHOT/api/stores')
            .then((response) => response.json())
            .then((data) => {
                console.log('Success:', data);
                setStores(data.stores);
                setInfoStore(data.info);
                setMessageStore(data.message);
            })
            .catch((error) => {
                console.error('Error:', error);
            });
    }

    const postApi = () => {
        fetch('http://localhost:8080/stock_fifo_lifo-1.0-SNAPSHOT/api/out', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                date: date,
                id_item: article,
                quantity: quantity,
                id_store: store
            }),
        })
            .then((response) => response.json())
            .then((data) => {
                console.log('Success:', data);
                setMessage(data.message);
                setStatusCode(data.status_code);
            })
            .catch((error) => {
                console.log('Error:', error);
            });
    }

    const handleFormSubmit = (e) => {
        e.preventDefault();
        console.log('Données du formulaire :', { article, quantity, unit_price, date, store });

        postApi();

        setQuantity('');
        setUnit_price('');
        setDate('');
        setArticle('');
        setStore('');
    };

    return (
        <div className="max-w-md mx-auto mt-8 p-6 bg-white rounded shadow-md">
            <h1 className="mb-4 text-3xl font-extrabold text-gray-900 dark:text-white md:text-5xl lg:text-6xl"><span className="text-transparent bg-clip-text bg-gradient-to-r to-emerald-600 from-sky-400">Out of Stock</span></h1>
            {message && (
                <div className={`text-center ${status_code === '201' ? 'text-green-500' : 'text-red-500'}`}>
                    {message}
                </div>
            )}
            <form onSubmit={handleFormSubmit} className="space-y-4">
                <div className="flex flex-col">
                    <label htmlFor="article" className="text-sm font-medium text-gray-600 mb-1">
                        Item:
                    </label>
                    <select
                        id="article"
                        value={article}
                        onChange={(e) => setArticle(e.target.value)}
                        className="px-3 py-2 border rounded focus:outline-none focus:ring focus:border-blue-300"
                    >
                        <option value="">-- Please choose --</option>
                        {items.map((item) => (
                            <option key={item.id} value={item.id}>
                                {item.name}
                            </option>
                        ))}
                    </select>
                </div>

                <div className="flex flex-col">
                    <label htmlFor="date" className="text-sm font-medium text-gray-600 mb-1">
                        Date:
                    </label>
                    <input
                        type="datetime-local"
                        id="date"
                        value={date}
                        onChange={(e) => setDate(e.target.value)}
                        className="px-3 py-2 border rounded focus:outline-none focus:ring focus:border-blue-300"
                    />
                </div>

                <div className="flex flex-col">
                    <label htmlFor="quantity" className="text-sm font-medium text-gray-600 mb-1">
                        Quantity:
                    </label>
                    <input
                        type="number"
                        id="quantity"
                        value={quantity}
                        onChange={(e) => setQuantity(e.target.value)}
                        className="px-3 py-2 border rounded focus:outline-none focus:ring focus:border-blue-300"
                    />
                </div>

                <div className="flex flex-col">
                    <label htmlFor="store" className="text-sm font-medium text-gray-600 mb-1">
                        Store:
                    </label>
                    <select
                        id="store"
                        value={store}
                        onChange={(e) => setStore(e.target.value)}
                        className="px-3 py-2 border rounded focus:outline-none focus:ring focus:border-blue-300"
                    >
                        <option value="">-- Please choose --</option>
                        {stores.map((item) => (
                            <option key={item.id} value={item.id}>
                                {item.name}
                            </option>
                        ))}
                    </select>
                </div>

                <button type="submit" className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">
                    Make an out
                </button>
            </form>
        </div>
    );
};

export default Form;
