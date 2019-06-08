<template>
    <div class="products container">
        <div v-show='toggle' class="" id="product_form">
            <AddProduct />
        </div>
        <div class="btn-bar">
            <button @click="toggle = !toggle" class="btn btn-success"> Add New...</button>
            <button @click="refresh" class="btn btn-secondary">Refresh</button>
        </div>
        <template v-for="product in products">
            <div class="card" style="margin-bottom:20px;">
                <div class="col-sm-8">
                    <p><strong>{{ product.name }}<span>&nbsp;x{{product.items}}</span></strong></p>
                </div><div class="col-sm-8">
                    <p>R{{ product.price }}</p>
                </div>

                <div class="col-sm-8">
                    <router-link :to="{path: '/api/products/'+product.id }">View Product</router-link>
                    <div>
                        <!--<Coins/>-->
                        Buy Product
                    </div>
                </div>
            </div>
        </template>
    </div>
</template>
<script>

    import axios from 'axios'
    import Vue from 'vue'
    import BootstrapVue from 'bootstrap-vue'
    import AddProduct from '@/components/AddProduct.vue'
    import Coins from '@/components/Coin.vue'
    Vue.use(BootstrapVue)

    const BASE_URL = 'http://localhost:8084/api/products';
    export default {
        name: 'Products',
        data() {
            return {
                products: [],
                toggle:false,
            }
        },
        methods: {
            addProduct(newProduct){
                this.products = [...this.products, newProduct];
            },
            load(e){
                axios.get(
                    BASE_URL
                ).then(
                    result => {
                        console.log(result.data);
                        this.products = result.data;
                    }
                ).catch(error => console.log(error))
            },
            refresh(e){
                this.load(e);
            }

        },
        created() {
            this.load();
        },
        mounted: function () {

        },
        components:{
            AddProduct,
            Coins,
        }
    }
</script>
<style>h3 {
    margin: 40px 0 0;
}
    .card{
        float: left;
        margin-right: 10px;
        padding: 20px;
        text-align: center;
        width: 30em;
    }
    .btn-bar{
        margin-bottom: 2em;
        text-align: center;
    }
    .btn-bar button{
        margin-right: 10px;
    }
</style>