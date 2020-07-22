<template>
<axios></axios>
</template>

<script>
import axios from 'axios';

axios({
    url: 'https://airtable.com/shrKWXmiLy6YZnZxx'
}).then((res) => {
    res.data.records
})

export default {

}
</script>

<style>

</style>