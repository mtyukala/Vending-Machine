<template>
    <div class="products container">
        <div class="title" style="margin-bottom:40px;">
            <h3>Coins in the System</h3>
        </div>
        <div class="row">
            <div class="col-sm-2">
                <h4>#</h4>
            </div>
            <div class="col-sm-8">
                <h4>Coin Details</h4>
            </div>
            <div class="col-sm-2">
                <h4>GO TO</h4>
            </div>
        </div>

        <template v-for="coin in coins">
            <div class="row" style="margin-bottom:20px;">
                <div class="col-sm-2" >
                    <p>{{ coin.id }}</p>
                </div>
                <div class="col-sm-8">
                    <p>{{ coin.description }}</p>
                </div>
                <div class="col-sm-8">
                    <p>R{{ coin.amount }}</p>
                </div>
                <div class="col-sm-8">
                    <p>x{{ coin.count }}</p>
                </div>
                <div class="col-sm-2">
                    <router-link :to="{path: '/api/coins/'+coin.id }">View Coin</router-link>
                </div>
            </div>
        </template>
    </div>
</template>

<script>
    import axios from 'axios'

    export default {
        name:'Coins',
        data(){
            return {
                coins:[]
            }
        },
        mounted:function(){
            axios.get(
                'http://localhost:8084/api/coins'
            ).then(
                result=>{
                    console.log(result);
                    this.coins=result.data;
                }
            )
        }
    }
</script>
<!--scoped means this stylesheet is limited for this vue file-->
<style scoped>


</style>