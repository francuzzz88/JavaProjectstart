<template>
    <v-app>
        <v-app-bar app>
            <v-toolbar-title>Message</v-toolbar-title>
            <v-btn rounded v-if="profile"
                   :disabled="$route.path==='/'"
                   @click="showMessages">
                Message
            </v-btn>
            <v-spacer></v-spacer>
            <v-btn rounded v-if="profile"
                   :disabled="$route.path==='/profile'"
                   @click="showProfile">
                {{profile.name}}
            </v-btn>

            <v-btn v-if="profile" icon href="/logout">
                <v-icon>exit_to_app</v-icon>
            </v-btn>
        </v-app-bar>
        <v-content>
            <router-view></router-view>
        </v-content>
    </v-app>
</template>

<script>
    import {mapState, mapMutations} from 'vuex'
    import MessagesList from 'pages/MessageList.vue'
    import {addHandler} from 'util/ws'

    export default {
        components: {
            MessagesList
        },
        computed: mapState(['profile']),
        methods: {
            ...mapMutations(['addMessageMutation', 'updateMessageMutation', 'removeMessageMutation']),
            showMessages() {
                this.$router.push('/')
            },
            showProfile() {
                this.$router.push('/profile')
            },
        },

        created() {
            addHandler(data => {
                if (data.objectType === 'MESSAGE') {

                    switch (data.eventType) {
                        case 'CREATE':
                            this.addMessageMutation(data, body)
                            break
                        case 'UPDATE':
                            this.updateMessageMutation(data, body)
                            break
                        case 'REMOVE':
                            this.removeMessageMutation(data, body)
                            break
                        default:
                            console.error('Looks like the event type if unknown "${eventType}"')
                    }

                } else {
                    console.error('Looks like the  object type if unknown "${objectType}"')
                }
            })
        }
        ,
        beforeMount() {
            if (!this.profile) {
                this.$router.replace('/auth')
            }
        }
    }
</script>

<style>

</style>