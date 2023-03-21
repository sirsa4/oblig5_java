<template id="hello-world">
  <div>
    <h1 class="hello-world">Hello, World!</h1>
    <a href="/tvserie/">View tvseries overview</a>
  </div>
</template>

<script>
app.component("hello-world", {template: "#hello-world"});
</script>

<style>
.hello-world {
  color: goldenrod;
}
</style>