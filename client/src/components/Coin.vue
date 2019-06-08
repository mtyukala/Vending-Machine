<template>
    <div class="coins container">


        <template v-for="coin in coins">
            <div class="row" style="margin-bottom:20px;">
                <div class="col-sm-2">
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
        name: 'Coins',
        data() {
            return {
                coins: []
            }
        },
        methods: {},
        created() {
            axios.get(
                'http://localhost:8084/api/coins'
            ).then(
                result => {
                    console.log(result.data);
                    this.coins = result.data;
                }
            ).catch(error => console.log(error))
        },
        mounted: function () {

        }
    }
</script>
<!--scoped means this stylesheet is limited for this vue file-->
<style scoped>
.coins{
    display: block;
}

</style>