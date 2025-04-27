package su.pank.ecards.data.di

import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.functions.Functions
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime
import org.koin.dsl.module
import su.pank.ecards.data.AuthRepository

val dataModule = module {
    single {
        createSupabaseClient("https://bniiaythgcqffeyzvxnw.supabase.co", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImJuaWlheXRoZ2NxZmZleXp2eG53Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDU2ODExMDAsImV4cCI6MjA2MTI1NzEwMH0.bOpPzFTJeNa3xUyGRrU0jrOdeEEJtgeIdowfy0n7wo0"){
            install(Auth)
            install(Postgrest)
            install(Realtime)
            install(Functions)
        }
    }
    single{
        AuthRepository(get())
    }
}