<template>
    <div class="products container">
        <div class="title" style="margin-bottom:40px;">
            <h3>Products on Sale</h3>
        </div>
        <div class="row">
            <div class="col-sm-2">
                <h4>#</h4>
            </div>
            <div class="col-sm-8">
                <h4>Product Details</h4>
            </div>
            <div class="col-sm-2">
                <h4>GO TO</h4>
            </div>
        </div>
    <template v-for="product in products">
        <div class="row" style="margin-bottom:20px;">
            <div class="col-sm-2" >
                <p>{{ product.id }}</p>
            </div>
            <div class="col-sm-8">
                <p>{{ product.name }}</p>
            </div>
            <div class="col-sm-2">
                <router-link :to="{path: '/api/products/'+product.id }">View Product</router-link>
            </div>
        </div>
    </template>
    </div>
</template><script>

    import axios from 'axios'

    export default {
        name:'Products',
        data(){
            return {
                products:[]
            }
        },
        methods:{

        },
        created(){
            axios.get(
                'http://localhost:8084/api/products'
            ).then(
                result=>{
                    console.log(result);
                    this.products=result.data;
                }
            ).catch(error=>console.log(error))
        },
        mounted:function(){

        }
    }
</script>
<style>h3{
    margin: 40px 0 0;
}
</style>