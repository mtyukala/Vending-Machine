<template>
    <div class="products container">
        <transition>
            <div v-show='toggle' class="" id="product_form">
                <AddProduct/>
            </div>
        </transition>
        <div class="btn-bar">
            <button @click="toggle = !toggle" class="btn btn-success"> Add New...</button>
            <button @click="refresh" class="btn btn-secondary">Refresh</button>
        </div>
        <p class="alert">{{output}}</p>
        <template v-for="product in products">
            <div class="card" style="margin-bottom:20px;">
                <div class="">
                    <p><strong>{{ product.name }}<span>&nbsp;x{{product.items}}</span></strong></p>
                </div>
                <div class="">
                    <p>R{{ product.price }}</p>
                </div>

                <div class="col-sm-8">
                    <!--<router-link :to="{path: '/api/products/'+product.id }" id="show-modal" @click="showModal = true">-->
                    <!--Buy {{product.name}}-->
                    <!--</router-link>-->
                </div>
                <div class="">
                    <button @click="showBuyModal" class="btn btn-primary" :data-id="product.id"
                            :data-url="'/api/products/'+product.id">Select
                        {{product.name}}
                    </button>
                </div>

            </div>
        </template>
        <div v-if="showModal">
            <transition name="modal">
                <div class="modal-mask">
                    <div class="modal-wrapper">

                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title">Modal title</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true" @click="showModal = false">&times;</span>
                                    </button>
                                </div>

                                <div class="modal-body">
                                    <p><strong>Insert Coins</strong></p>
                                    <label>
                                        <select @change="insertCoin" class="small">

                                            <option v-for="coin in coins"
                                                    v-bind:value="{id:coin.id,text:coin.description}"
                                                    :data-coin="coin.description">{{coin.description}}
                                            </option>

                                        </select>
                                    </label>
                                    <p style="margin-top: 1em;"><strong
                                    >Inserted:</strong> R{{total/100}}</p>
                                    <!--<button @click="insertCoin">Insert Coin</button>-->
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" @click="cancel">Cancel
                                    </button>
                                    <button type="button" class="btn btn-primary" @click="buy">Buy</button>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </transition>
        </div>


    </div>
</template>
<script>

    import axios from 'axios'
    import Vue from 'vue'
    import BootstrapVue from 'bootstrap-vue'
    import AddProduct from '@/components/AddProduct.vue'
    import Coins from '@/components/Coin.vue'

    Vue.use(BootstrapVue)

    const BASE_URL = 'http://localhost:8084';
    const PRODUCTS_URL = BASE_URL + '/api/products';
    const COINS_URL = BASE_URL + '/api/coins';
    const BUY_URL = BASE_URL + '/api/buy';
    export default {
        name: 'Products',
        data() {
            return {
                products: [],
                coins: [],
                inserted_coins: [],
                product_id: '',
                toggle: false,
                showModal: false,
                output: '',
                insertedCoins: [],
                name: '',
                total: '',
            }
        },
        methods: {
            buy: (e) => {
                let product = this.products.find(product => parseInt(product.id) === parseInt(this.product_id));
                let p = {};
                p['id'] = product.id;
                p['name'] = product.name;
                p['price'] = product.price;
                p['weight'] = product.weight;
                p['items'] = product.items;
                let headers = {
                    'Content-Type': 'application/json'
                    //    "Content-Type": "application/x-www-form-urlencoded"
                };
                axios.post(BUY_URL, JSON.stringify({
                    product: p, amount: this.total, quantity: 1,
                }), {headers: headers}).then(result => {
                        //this.output = result.data;
                        if (result.status === 201) {
                            let message = "Purchase performed successfully";// + result.data;
                            message += ". ";// + result.data;//.find('changeInCoins');
                            this.output = result.data;//message;
                            this.refresh(e);
                            this.showModal = false;
                        }
                    }
                ).catch(err => {
                    this.output = err;
                    this.cancel(e);
                })

            },
            insertCoin: (e) => {
                let i = e.target.selectedIndex;
                if (i >= 0) {
                    this.inserted_coins.push(this.coins[i]);
                    this.total = this.sum(e);
                }
            },
            sum: (e) => {
                let total = 0;
                for (let index = 0; index < this.inserted_coins.length; index++) {
                    total += this.inserted_coins[index].amount;
                }

                return total;
            },
            addProduct: (newProduct) => {
                this.products = [...this.products, newProduct];
            },
            load: (e) => {
                axios.get(PRODUCTS_URL).then(
                    result => {
                        console.log(result.data);
                        this.products = result.data;
                    }
                ).catch(error => console.log(error));
                axios.get(COINS_URL).then(
                    response => {
                        console.log(response.data);
                        this.coins = response.data;
                    }
                ).catch(err => console.log(err));
            },
            refresh: (e) => {
                this.load(e);
            },
            cancel: (e) => {
                this.showModal = false;
                this.total = 0;
                this.inserted_coins = [];
            },

            showBuyModal: (e) => {
                this.showModal = true;
                this.output = e.target.getAttribute('data-url');
                this.product_id = e.target.getAttribute('data-id');
            },

        },
        created() {
            this.load(); // --- get products and coins from the server on page load
        },

        components: {
            AddProduct,
            Coins,
        },
    }

</script>
<style>h3 {
    margin: 40px 0 0;
}

.products {
    height: 100%;
}

.card {
    float: left;
    margin-right: 10px;
    padding: 20px;
    text-align: center;
    width: 22em;
}

.btn-bar {
    margin-bottom: 2em;
    text-align: center;
}

.btn-bar button {
    margin-right: 10px;
}

.modal-mask {
    position: fixed;
    z-index: 9998;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, .5);
    display: table;
    transition: opacity .3s ease;
}

.modal-wrapper {
    display: table-cell;
    vertical-align: middle;
}
</style>