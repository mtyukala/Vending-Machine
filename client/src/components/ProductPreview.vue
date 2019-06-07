<template>
    <div class="preview">
        <div class="row">
            <div class="col-sm-6">
                <!--  this part will contain the product info -->
                <h1> {{ product_name }} [x {{product_items}}] </h1>
                <div>
                    <p> {{ product_weight }} </p>
                    <p> Price : ${{ product_price }} </p>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
    from
    axios
    import 'axios';


    export default {
        name: 'Product Preview',
        data() {
            return {
                product_name: "",
                product_price: "",
                product_items: "",
                product_weight: "",
                product_img_url: "",
            }
        }, mounted: function () {
            axios.get('http://localhost:8084/api/products/${this.$route.params.id}').then(
                res => {
                    this.product_name = res.data.name;
                    this.product_price = res.data.price;
                    this.product_items = res.data.items;
                    this.product_weight = res.data.weight;
                    this.product_img_url = res.data.pictureUrl;
                }
            )
        }
    }

</script>