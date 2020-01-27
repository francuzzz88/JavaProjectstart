<template>
    <span></span>
</template>

<script>
    import {mapActions} from 'vuex'
    export default {
        name: 'LazyLoader',
        methods: mapActions(['loadPageAction']),
        mounted() {
            window.onscroll = () => {
                const element =document.documentElement
                const isBottomOfScren = element.scrollTop + window.innerHeight ===element.offsetHeight

                if(isBottomOfScren){
                    this.loadPageAction()
                }
            }
        },
        beforeDestroy() {
            window.onscroll = null
        }
    }
</script>

<style scoped>

</style>