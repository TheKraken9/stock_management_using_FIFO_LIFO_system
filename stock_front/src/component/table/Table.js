import React, {useEffect, useState} from 'react';
import 'tailwindcss/tailwind.css';
import moment from 'moment';

const Table = () => {
    const [date1, setDate1] = useState('');
    const [article, setArticle] = useState('');
    const [store, setStore] = useState('');
    const [quantity, setQuantity] = useState('');
    const [unit_price, setUnit_price] = useState('');
    const [date, setDate] = useState('');
    const [date2, setDate2] = useState('');
    const [average, setAverage] = useState('');
    const [total, setTotal] = useState('');
    const [id_store, setIdStore] = useState('');
    const [id_item, setIdItem] = useState('');
    const [searchResults, setSearchResults] = useState([]);
    const [items, setItems] = useState([]);
    const [stores, setStores] = useState([]);

    const [messageItem, setMessageItem] = useState('');
    const [infoItem, setInfoItem] = useState('');
    const [errorItem, setErrorItem] = useState('');

    const [messageStore, setMessageStore] = useState('');
    const [infoStore, setInfoStore] = useState('');
    const [errorStore, setErrorStore] = useState('');

    useEffect(() => {
        getItem();
        getStore();
    }, []);

    const getItem = () => {
        fetch('http://localhost:8080/stock_fifo_lifo-1.0-SNAPSHOT/api/items/gnl')
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

    const handleSearch = () => {
        fetchResults();
    };

    const fetchResults = () => {
        const formatedDate1 = moment(date1).format('YYYY-MM-DD HH:mm:ss');
        const formatedDate2 = moment(date2).format('YYYY-MM-DD HH:mm:ss');
        const apiUrl = `http://localhost:8080/stock_fifo_lifo-1.0-SNAPSHOT/api/stocks/state?date1=${formatedDate1}&date2=${formatedDate2}&id_item=${article}&id_Store=${store}`;

        console.log(apiUrl);
        fetch(apiUrl)
            .then((response) => response.json())
            .then((data) => {
                console.log('Résultats de la recherche :', data);
                setSearchResults(data.stock);
            })
            .catch((error) => {
                console.error('Erreur lors de la recherche :', error);
            });
    };

    return (
        <div className="container mx-auto mt-8 p-6 bg-white rounded shadow-md">
            {/*<h2 className="text-2xl font-semibold mb-6">State of stock</h2>
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
                <div className="flex flex-col space-y-4">
                    <label className="text-sm font-medium text-gray-600">
                        INITIAL DATE
                        <input type="datetime-local" value={date1} onChange={(e) => setDate1(e.target.value)} className="px-4 py-2 border rounded focus:outline-none focus:ring focus:border-blue-300" />
                    </label>
                    <label className="text-sm font-medium text-gray-600">
                        FINAL DATE
                        <input type="datetime-local" value={date2} onChange={(e) => setDate2(e.target.value)} className="px-4 py-2 border rounded focus:outline-none focus:ring focus:border-blue-300" />
                    </label>
                    <label className="text-sm font-medium text-gray-600">
                        STORE
                        <input type="text" value={id_store} onChange={(e) => setIdStore(e.target.value)} className="px-4 py-2 border rounded focus:outline-none focus:ring focus:border-blue-300" />
                    </label>
                    <label className="text-sm font-medium text-gray-600">
                        ITEM
                        <input type="text" value={id_item} onChange={(e) => setIdItem(e.target.value)} className="px-4 py-2 border rounded focus:outline-none focus:ring focus:border-blue-300" />
                    </label>
                    <button onClick={handleSearch} className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">
                        Search
                    </button>
                </div>
            </div>*/}


            <div className="max-w-md mx-auto mt-8 p-6 bg-white rounded shadow-md">
                <h1 className="mb-4 text-3xl font-extrabold text-gray-900 dark:text-white md:text-5xl lg:text-6xl"><span className="text-transparent bg-clip-text bg-gradient-to-r to-emerald-600 from-sky-400">State of Stock</span></h1>
                <div className="space-y-4">
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
                        <label htmlFor="date1" className="text-sm font-medium text-gray-600 mb-1">
                            Initial date:
                        </label>
                        <input
                            type="datetime-local"
                            id="date1"
                            value={date1} onChange={(e) => setDate1(e.target.value)}
                            className="px-3 py-2 border rounded focus:outline-none focus:ring focus:border-blue-300"
                        />
                    </div>

                    <div className="flex flex-col">
                        <label htmlFor="date2" className="text-sm font-medium text-gray-600 mb-1">
                            Final date:
                        </label>
                        <input
                            type="datetime-local"
                            id="date2"
                            value={date2} onChange={(e) => setDate2(e.target.value)}
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
                    <button onClick={handleSearch} className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">
                        Search
                    </button>
                </div>

            </div>


            <div className="relative overflow-x-auto shadow-md sm:rounded-lg">
                <table className="w-full text-sm text-left rtl:text-right text-blue-100 dark:text-blue-100">
                    <thead className="text-xs text-white uppercase bg-blue-600 border-b border-blue-400 dark:text-white">
                    <tr>
                        <th scope="col" className="px-6 py-3 bg-blue-500">
                            Item
                        </th>
                        <th scope="col" className="px-6 py-3">
                            Initial quantity
                        </th>
                        <th scope="col" className="px-6 py-3 bg-blue-500">
                            Rest
                        </th>
                        <th scope="col" className="px-6 py-3">
                            Unit
                        </th>
                        <th scope="col" className="px-6 py-3 bg-blue-500">
                            Average of unit price
                        </th>
                        <th scope="col" className="px-6 py-3">
                            Total
                        </th>
                        <th scope="col" className="px-6 py-3 bg-blue-500">
                            Store
                        </th>
                    </tr>
                    </thead>
                    <tbody>

                    <tr className="bg-blue-600 border-b border-blue-400">
                        <th scope="row" className="px-6 py-4 font-medium bg-blue-500 text-blue-50 whitespace-nowrap dark:text-blue-100">
                            {searchResults.item}
                        </th>
                        <td className="px-6 py-4">
                            {searchResults.initial_quantity}
                        </td>
                        <td className="px-6 py-4 bg-blue-500">
                            {searchResults.rest_quantity}
                        </td>
                        <td className="px-6 py-4">
                            {searchResults.unit}
                        </td>
                        <td className="px-6 py-4 bg-blue-500">
                            {searchResults.average_unit_price}
                        </td>
                        <td className="px-6 py-4">
                            {searchResults.total}
                        </td>
                        <td className="px-6 py-4 bg-blue-500">
                            {searchResults.store}
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default Table;
